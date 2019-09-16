package com.gcxy.Utils;

import java.io.*;

/**
 * @author HX
 * @title: FileOp
 * @projectName E_Show
 * @date 2019/8/13  12:54
 */
public class FileOp {
	public static Boolean ImageWrite(String SourcePath,String DestPath){
		Boolean ret = false;
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream( new File( SourcePath ) );
			os = new FileOutputStream( new File( DestPath )  );
			int index;
			byte[] buf = new byte[1024];
			while ((index = is.read( buf )) != -1){
				os.write( buf,0,index );
			}
			ret = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	public static Boolean FileExist(String filePath){
		File file = new File( filePath );
		return file.exists();
	}

	public static String pathChange(String WebSrc){
		String filePath = "/D:/Junior/E_Shop/out/artifacts/E_Shop_war_exploded/WEB-INF/classes/admin.xml";
		String newPath = "";
		String[] split = filePath.split( "/" );
		for(int i = 0 ; i <4;i++){
			newPath += split[i] + "/";
		}
		newPath +=  "src/admin.xml";
		return newPath;
	}
}
