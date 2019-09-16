package com.gcxy.Utils;

import com.gcxy.pojo.Admin;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.*;
import org.dom4j.io.XMLWriter;

/**
 * @author HX
 * @title: XmlOpreation
 * @projectName E_Show
 * @date 2019/8/5  12:34
 */
public class XmlOpreation {
	private static List<Admin> alladmins = null;

	private static SAXReader reader = null;
	private static Document read = null;
	private static Element rootElement;

	static {
		reader = new SAXReader();
		reader.setEncoding( "GBK" );
	}

	public static List<Admin> selAllAdmin(String filePath) throws Exception{
		alladmins = new ArrayList<>(  );
		read = reader.read( filePath );
		rootElement = read.getRootElement();
		for(Iterator eit = rootElement.elementIterator();eit.hasNext();){
			Element admins = (Element)eit.next();
			Admin admin = new Admin();
			int count = 0;
			for(Iterator it = admins.attributeIterator();it.hasNext();){
				Attribute at = (Attribute)it.next();
				String value = at.getValue();
				switch (count){
					case 0:
						admin.setId( value );
						break;
					case 1:
						admin.setPwd( value );
						break;
					case 2:
						admin.setName( value );
						break;
					case 3:
						admin.setFlag( Integer.parseInt( value ) );
						break;
					case 4:
						admin.setSex( value );
						break;
					case 5:
						admin.setBorntime( value );
						break;
					case 6:
						admin.setEmail( value );
						break;
					case 7:
						admin.setPhone( value );
						break;
					case 8:
						admin.setAddress( value );
						break;
					case 9:
						admin.setPostalcode( value );
						break;
					case 10:
						admin.setRegistertime( value );
						break;
				}
				count++;
			}
			alladmins.add( admin );
		}
		return alladmins;
	}

	public static Boolean updAdminsPwd(String filePath,String adminID,String newPwd) throws DocumentException {
		Boolean ret = false;
		read = reader.read( filePath );
		rootElement = read.getRootElement();
		if(hasAdmin( filePath,adminID )){
			for(Iterator eit = rootElement.elementIterator();eit.hasNext();){
				Element admin = (Element)eit.next();
				for(Iterator it = admin.attributeIterator();it.hasNext();){
					Attribute at = (Attribute)it.next();
					String name = at.getName();
					String value = at.getValue();
					if(name.equals( "id" ) && adminID.equals( value )){
						Attribute modify = (Attribute)it.next();
						modify.setValue( newPwd );
					}
				}
			}
			XMLWriter writer = null;
			try {

				OutputFormat format = OutputFormat.createPrettyPrint() ;
				format.setEncoding( "GBK" );
				writer = new XMLWriter(new FileWriter(filePath),format);
				writer.write(read);
				writer.close();
				ret = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static Admin selByAdminID(String filePath,String AdminID) throws DocumentException {
		read = reader.read( filePath );
		rootElement = read.getRootElement();
		Admin retAdmin = new Admin();
		if(hasAdmin( filePath,AdminID )){
			for(Iterator eit = rootElement.elementIterator();eit.hasNext();){
				Element admins = (Element)eit.next();
				Attribute id = admins.attribute( "id" );
				if(id.getValue().equals( AdminID )){
					Attribute pwd = admins.attribute( "pwd" );
					Attribute name = admins.attribute( "name" );
					Attribute flag = admins.attribute( "flag" );
					Attribute sex = admins.attribute( "sex" );
					Attribute borntime = admins.attribute( "borntime" );
					Attribute email = admins.attribute( "email" );
					Attribute phone = admins.attribute( "phone" );
					Attribute address = admins.attribute( "address" );
					Attribute postalcode = admins.attribute( "postalcode" );
					Attribute registertime = admins.attribute( "registertime" );

					retAdmin.setId( id.getValue() );
					retAdmin.setPwd( pwd.getValue() );
					retAdmin.setName( name.getValue() );
					retAdmin.setFlag( Integer.parseInt( flag.getValue() ) );
					retAdmin.setSex( sex.getValue() );
					retAdmin.setBorntime( borntime.getValue() );
					retAdmin.setEmail( email.getValue() );
					retAdmin.setPhone( phone.getValue() );
					retAdmin.setAddress( address.getValue() );
					retAdmin.setPostalcode( postalcode.getValue() );
					retAdmin.setRegistertime( registertime.getValue() );
				}
			}
		}else {
			retAdmin = null;
		}
		return retAdmin;
	}

	public static Boolean hasAdmin(String filePath,String AdminID) throws DocumentException {
		read = reader.read( filePath );
		rootElement = read.getRootElement();
		Boolean ret = false;
		for(Iterator eit = rootElement.elementIterator();eit.hasNext();){
			Element admins = (Element)eit.next();
			Attribute id = admins.attribute( "id" );
			if(id.getValue().equals( AdminID )){
				ret = true;
			}
		}
		return ret;
	}

	public static Boolean insAdmin(String filePath,Admin admin) throws DocumentException {
		Boolean ret = false;
		read = reader.read( filePath );
		rootElement = read.getRootElement();
		if(!hasAdmin( filePath,admin.getId() )){
			Element adminNode = rootElement.addElement( "admin" );
			adminNode.addAttribute( "id",admin.getId() );
			adminNode.addAttribute( "pwd",admin.getPwd() );
			adminNode.addAttribute( "name",admin.getName() );
			adminNode.addAttribute( "flag",String.valueOf( admin.getFlag() ) );
			adminNode.addAttribute( "sex",admin.getSex() );
			adminNode.addAttribute( "borntime",admin.getBorntime() );
			adminNode.addAttribute( "email",admin.getEmail() );
			adminNode.addAttribute( "phone",admin.getPhone() );
			adminNode.addAttribute( "address",admin.getAddress() );
			adminNode.addAttribute( "postalcode",admin.getPostalcode() );
			adminNode.addAttribute( "registertime",admin.getRegistertime() );
			XMLWriter writer = null;
			try {
				OutputFormat format = OutputFormat.createPrettyPrint() ;
				format.setIndent(true) ;
				format.setIndent("\t") ;
				format.setLineSeparator("\n") ;
				writer = new XMLWriter(new FileWriter(filePath),format);
				writer.write(read);
				writer.close();
				ret = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static Boolean delAdmin(String filePath,String AdminID) throws DocumentException {
		read = reader.read( filePath );
		rootElement = read.getRootElement();
		Boolean ret = false;
		if(hasAdmin( filePath,AdminID )){
			for(Iterator eit = rootElement.elementIterator();eit.hasNext();){
				Element admins = (Element)eit.next();
				Attribute id = admins.attribute( "id" );
				if(id.getValue().equals( AdminID )){
					rootElement.remove( admins );
				}
			}
			XMLWriter writer = null;
			try {
				writer = new XMLWriter(new FileWriter(filePath));
				writer.write(read );
				writer.close();
				ret = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static Boolean updAdminInfor(String filePath,Admin ad) throws DocumentException {
		Boolean ret = false;
		read = reader.read( filePath );
		rootElement = read.getRootElement();
		if(hasAdmin( filePath,ad.getId() )){
			for(Iterator eit = rootElement.elementIterator();eit.hasNext();){
				Element admin = (Element)eit.next();
				for(Iterator it = admin.attributeIterator();it.hasNext();){
					Attribute at = (Attribute)it.next();
					String name = at.getName();
					String value = at.getValue();
					if(name.equals( "id" ) && ad.getId().equals( value )){
						while (it.hasNext()){
							Attribute modify = (Attribute)it.next();
							if(modify.getName().equals( "name" )){
								modify.setValue( ad.getName() );
							}else if(modify.getName().equals( "sex" )){
								modify.setValue( ad.getSex() );
							}else if(modify.getName().equals( "borntime" )){
								modify.setValue( ad.getBorntime() );
							}else if(modify.getName().equals( "email" )){
								modify.setValue( ad.getEmail() );
							}else if(modify.getName().equals( "phone" )){
								modify.setValue( ad.getPhone() );
							}else if(modify.getName().equals( "address" )){
								modify.setValue( ad.getAddress() );
							}else if(modify.getName().equals( "postalcode" )){
								modify.setValue( ad.getPostalcode() );
							}
							ret = true;
						}
					}
					else{
						break;
					}
				}
			}
			XMLWriter writer = null;
			try {
				OutputFormat format = OutputFormat.createPrettyPrint() ;
				format.setEncoding( "GBK" );
				writer = new XMLWriter(new FileWriter(filePath),format);
				writer.write(read);
				writer.close();
				ret = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
