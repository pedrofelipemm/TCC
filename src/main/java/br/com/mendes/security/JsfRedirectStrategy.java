package br.com.mendes.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.util.UrlUtils;

public class JsfRedirectStrategy extends DefaultRedirectStrategy {

	private boolean contextRelative;

	private static final String PARTIAL_RESPONSE_PREFIX = "<partial-response><redirect url=\"";
	private static final String PARTIAL_RESPONSE_SUFFIX = "\"/></partial-response>";

	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {

		if (isAjaxRequest(request)) {

			try {

				if (StringUtils.contains(url, "&")) {
					url = StringUtils.replace(url, "&", "&amp;");
				}

				returnPartialResponseRedirect(request, response, url);
				return;

			} catch (Exception e) {
				this.logger.error("[sendRedirect: failed]", e);
			}
		}

		super.sendRedirect(request, response, url);
	}

	private boolean isAjaxRequest(HttpServletRequest request) {

		String header = request.getHeader("Faces-Request");
		String param = request.getParameter("javax.faces.partial.ajax");

		return ("partial/ajax".equals(header) || "true".equals(param)) ? true : false;

	}

	private void returnPartialResponseRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {

		String redirectUrl = calculateRedirectUrl(request.getContextPath(), url);
		redirectUrl = response.encodeRedirectURL(redirectUrl);

		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");

		String responseStr = PARTIAL_RESPONSE_PREFIX + redirectUrl + PARTIAL_RESPONSE_SUFFIX;

		PrintWriter out = response.getWriter();
		out.write(responseStr);
		out.flush();
		out.close();

	}

	protected String calculateRedirectUrl(String contextPath, String url) {

		if (!UrlUtils.isAbsoluteUrl(url)) {
			return this.contextRelative ? url : contextPath + url;
		}

		if (!this.contextRelative) {
			return url;
		}

		url = url.substring(url.indexOf("://") + 3);
		url = url.substring(url.indexOf(contextPath) + contextPath.length());

		if (url.length() > 1 && url.charAt(0) == '/') {
			url = url.substring(1);
		}

		return url;
	}

	@Override
	public void setContextRelative(boolean useRelativeContext) {
		this.contextRelative = useRelativeContext;
	}

}
