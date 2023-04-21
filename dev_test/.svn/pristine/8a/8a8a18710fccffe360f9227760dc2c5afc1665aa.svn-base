/**
 * @version 3.2.0.1
 */
package exts.com.pagi;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class CpfPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;
	
	public CpfPaginationRenderer() {
	}
	public void initVariables() {
		firstPageLabel = "<li class=\"first_page_btn\"><a href=\"#none\" onclick=\"{0}({1}); return false;\"><span>첫 페이지</span></a></li>"; 
        previousPageLabel = "<li class=\"prev_page_btn\"><a href=\"#none\" onclick=\"{0}({1}); return false;\"><span>이전 페이지</span></a></li>";
        currentPageLabel = "<li class=\"on\"><a href=\"#none\">{0}</a></li>";
        otherPageLabel = "<li><a href=\"#none\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
        nextPageLabel = "<li class=\"next_page_btn\"><a href=\"#none\" onclick=\"{0}({1}); return false;\"><span>다음 페이지</span></a></li>";
        lastPageLabel = "<li class=\"last_page_btn\"><a href=\"#none\" onclick=\"{0}({1}); return false;\"><span>마지막 페이지</span></a></li>";
	}

	@Override
	public String renderPagination(PaginationInfo paginationInfo,
			String jsFunction) {
		
		return super.renderPagination(paginationInfo, jsFunction);
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}
