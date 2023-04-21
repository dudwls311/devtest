
package exts.com.vo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * EgovMap 확장
 * @author KMK
 *
 */
@SuppressWarnings("serial")
public class ComEgovMap extends EgovMap {

	protected Log log = LogFactory.getLog(this.getClass());

	public String getNullString(String str) {
		String rtn = "";
		try {
			if (get(str) != null)
				rtn = String.valueOf(get(str));
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return rtn;
	}

	public String getString(String str) {
		String rtn = "";
		try {
			if (get(str) != null)
				rtn = NullUtil.nullString(String.valueOf(get(str)));
		} catch ( NullPointerException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return rtn;
	}

	public int getInt(String str) {
		return getInt(str, 0);
	}

	public int getInt(String str, int defaultValue) {
		int rtn = -1;
		try {
			if (get(str) != null)
				rtn = Integer.valueOf((get(str)).toString());
			if (rtn < 0) {
				rtn = 0;
			}
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return rtn;
	}

	public double getDouble(String str) {
		return getDouble(str, 0);
	}

	public double getDouble(String str, int defaultValue) {
//		double rtn = 0;
		double rtn = defaultValue;
		try {
			if (get(str) != null)
				rtn = Double.valueOf((get(str)).toString());
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return rtn;
	}

}
