package fr.esiea.web.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import fr.esiea.web.bean.LongLatBean;
import fr.esiea.web.bean.SearchFormBean;
import fr.esiea.web.entity.Artist;
import fr.esiea.web.entity.Shop;
import fr.esiea.web.service.ArtistService;
import fr.esiea.web.service.ShopService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"searchFormBean","listLongLatBeans"})
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	/**
	 * Les services qui se connectent à la base de données
	 */
	@Autowired
	private ShopService shopService;
	@Autowired
	private ArtistService artistService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home( ModelMap model) {
		
		List<Shop>listShop=shopService.listShop();
		for(Shop shop:listShop){
			System.out.println(shop.toString());
		}
		List<Artist>listArtist=artistService.listArtist();
		for(Artist artist:listArtist){
			System.out.println(artist.toString());
		}
		
		ModelAndView mav=new ModelAndView("home");
		
		SearchFormBean searchFormBean=new SearchFormBean();
		searchFormBean.setListShops(listShop);
		searchFormBean.setListArtist(listArtist);
		
		model.put("searchFormBean",searchFormBean);
		model.addAttribute("serverTime", "Map:" );
		
		return mav;
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView getSearch(ModelMap model,
			@ModelAttribute("searchFormBean")SearchFormBean searchFormBean) {
		
		//
		List<Shop>listShop=shopService.listShop();
		List<Artist>listArtist=artistService.listArtist();
		
		Shop shop=null;
		if(searchFormBean.getIdPathSelectedCity()!=0){
			shop=shopService.getShopById(searchFormBean.getIdPathSelectedCity());
		}
		Artist artist=null;
		if(searchFormBean.getIdPathSelectedStyle()!=0){
			artist=artistService.getArtistById(searchFormBean.getIdPathSelectedStyle());
		}
		
		//Recherche avec city et style
		List<LongLatBean> listLongLatBeans=new ArrayList<LongLatBean>();
		LongLatBean longLatBean=null;
		if(shop!= null && artist!=null){
			for(Shop shopL:listShop){
				for(Artist artistL:listArtist) {
					if(artistL.getStyle().equals(artist.getStyle()) && artistL.getIdShop()==shopL.getId() 
							&& shopL.getCity().equals(shop.getCity()) ){
						System.out.println(shopL.getName());
						
						longLatBean=getShopLongLat(shopL);
						
						listLongLatBeans.add(longLatBean);
						System.out.println(longLatBean.getLatitude() + " ; " + longLatBean.getLongitude());
					}
				}
				
			}
		//Recherche avec city et sans style	
		}else if(shop!= null && artist==null){
			for(Shop shopL:listShop){
				if(shopL.getCity().equals(shop.getCity())){
					longLatBean=getShopLongLat(shopL);
					listLongLatBeans.add(longLatBean);
				}
			}
			
		//Recherche avec style et sans city	
		}else if(shop== null && artist!=null){
			for(Shop shopL:listShop){
				for(Artist artistL:listArtist){
					if(artistL.getStyle().equals(artist.getStyle()) && artistL.getIdShop()==shopL.getId()) {
						longLatBean=getShopLongLat(shopL);
						listLongLatBeans.add(longLatBean);
					}
				}
			}
		}
		
		//On ajoute la liste sur le model 
		model.put("listLongLatBeans", listLongLatBeans);
		
		ModelAndView mav=new ModelAndView("home");
		return mav;
		
	}
	
	
	
	private LongLatBean getShopLongLat(Shop shop) {
		LongLatBean longLatBean = new LongLatBean();
		//On recupere les coordonnées
		Map<String,Double> mapLongLat=getLongitudeLatitude(shop);
		
		//On les ajoute au bean
		longLatBean.setLatitude(mapLongLat.get("latitude"));
		longLatBean.setLongitude(mapLongLat.get("longitude"));
		longLatBean.setLabel(shop.getName());
		
		return longLatBean;
	}



	private static final String GEOCODE_REQUEST_URL = "http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&";
    private static HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
    
    /**
     * 
     * @param addressEntity
     * @return
     */
    public Map<String,Double> getLongitudeLatitude(Shop shop) {
    	Map<String,Double> longLatMap=new HashMap<String, Double>();
        try {
        	
        	String adress=shop.getNumber()+" "+shop.getStreet()+" "+shop.getZipcode()+" "+shop.getCity();
            
        	
        	
        	StringBuilder urlBuilder = new StringBuilder(GEOCODE_REQUEST_URL);
            urlBuilder.append("&address=").append(URLEncoder.encode(adress, "UTF-8"));
            
            logger.debug("Searching Latitude Longitude by adress user from google api ...");
            
            final GetMethod getMethod = new GetMethod(urlBuilder.toString());
            try {
                httpClient.executeMethod(getMethod);
                Reader reader = new InputStreamReader(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
                 
                int data = reader.read();
                char[] buffer = new char[1024];
                Writer writer = new StringWriter();
                while ((data = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, data);
                }
 
                String result = writer.toString();
                logger.debug(result.toString());
                
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader("<"+writer.toString().trim()));
                Document doc = db.parse(is);
             
                String strLatitude = getXpathValue(doc, "//GeocodeResponse/result/geometry/location/lat/text()");
                logger.debug("Latitude:" +strLatitude);
                longLatMap.put("latitude", new Double(strLatitude));
                
                String strLongtitude = getXpathValue(doc,"//GeocodeResponse/result/geometry/location/lng/text()");
                logger.debug("Longitude:" + strLongtitude);
                longLatMap.put("longitude", new Double(strLongtitude));
                 
                 
            } finally {
                getMethod.releaseConnection();
            }
        } catch (Exception e) {
             e.printStackTrace();
        }
		return longLatMap;
    }
    /**
     * 
     * @param doc
     * @param strXpath
     * @return
     * @throws XPathExpressionException
     */
    private String getXpathValue(Document doc, String strXpath) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xPath.compile(strXpath);
        String resultData = null;
        Object result4 = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result4;
        for (int i = 0; i < nodes.getLength(); i++) {
            resultData = nodes.item(i).getNodeValue();
        }
        return resultData;
    }
	
	
	
	
}
