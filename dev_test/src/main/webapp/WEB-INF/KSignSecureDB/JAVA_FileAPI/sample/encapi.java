import com.ksign.securedb.fileapi.SDBDupException;
import com.ksign.securedb.fileapi.SDBFileAPI;
import com.ksign.securedb.fileapi.SDBFileAPIException;

public class encapi {
	public static void main(String[] args) {
		String policyName = "DBSEC.M_KEY.ARIA256";
		String sourcePath = "/home/tomcat/KSignSecureDB/JAVA_FileAPI/img/A.JPG";
		String targetPath = "/home/tomcat/KsignSecureDB/JAVA_FileAPI/img/enc/A.JPG";
		boolean isDelSourceFile = false;

		try {
			long encstarttime = System.currentTimeMillis();

			boolean result = SDBFileAPI.encryptFileFD(policyName, sourcePath, isDelSourceFile);
			long encendtime = System.currentTimeMillis();
			System.out.println("Enc Time : " + (encendtime - encstarttime)*0.001 + "sec");

			//result = SDBFileAPI.decryptFileFD(policyName, sourcePath);
			//result = SDBFileAPI.encryptFileT(policyName, sourcePath, targetPath, isDelSourceFile);
			//result = SDBFileAPI.decryptFileT(policyName, sourcePath, targetPath);
		} catch (SDBDupException dupEx) {
			//...
		} catch (SDBFileAPIException faEx) {
			//...
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
