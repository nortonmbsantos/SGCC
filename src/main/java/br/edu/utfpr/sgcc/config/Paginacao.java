package br.edu.utfpr.sgcc.config;

import java.util.HashMap;

public class Paginacao {

	public static String geraPaginacao(int currentPage, int totalPages, String idForm, String page) {
		return geraPaginacao(currentPage, totalPages, idForm, page, "", "");
	}

	public static String geraPaginacao(int currentPage, int totalPages, String idForm, String page, String key,
			String value) {
		return geraPaginacao(currentPage, totalPages, idForm, page, key, value, new HashMap<String, String>());
	}

	public static String geraPaginacao(int currentPage, int totalPages, String idForm, String page,
			HashMap<String, String> map) {
		return geraPaginacao(currentPage, totalPages, idForm, page, "", "", map);
	}

	public static String geraPaginacao(int currentPage, int totalPages, String idForm, String page, String key,
			String value, HashMap<String, String> map) {
		String paginacaoForm = "";

		if (totalPages > 1) {
			paginacaoForm = " <div class=\"row paginacao\">\n"
					+ "            <div class=\"col d-flex justify-content-center\">\n"
					+ "                <nav aria-label=\"...\">\n" + "                    <ul class=\"pagination\">\n"
					+ "                        <li class=\"page-item ";

			if (currentPage == 1) {
				paginacaoForm += "disabled \">";
			} else {
				paginacaoForm += "\">";
			} 
			paginacaoForm += "<form action=\""+ idForm +"\" method=\"POST\" id=\"" + idForm + "\">\n" + " <input type=\"hidden\" name=\"" + page
					+ "\" value=\"1\">\n";
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				paginacaoForm += " <input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + entry.getValue()
						+ "\">\n";
			}
			paginacaoForm += " <input type=\"hidden\" name=\"value\" value=\"" + value + "\">\n"
					+ " <input type=\"hidden\" name=\"key\" value=\"" + key + "\">\n"
					+ " <button class=\"page-link\">Primeira página</button>\n" + "</form>\n" + " </li>\n";

			if ((currentPage + 2) >= totalPages && currentPage > 3) {
				for (int i = totalPages - 4; i <= totalPages; i++) {
					if (i == currentPage) {
						paginacaoForm += "<li class=\"page-item active\">\n" + "<span class=\"page-link active\">\n"
								+ "" + i + "\n" + "<span class=\"sr-only\">(atual)</span>\n" + "</span>\n" + "</li>\n";

					} else {
						paginacaoForm += "<li class=\"page-item\">\n" + "<form action=\""+ idForm +"\" method=\"POST\" id=\"" + idForm + "\">\n"
								+ "<input type=\"hidden\" name=\"" + page + "\" value=\"" + i + "\">\n";
						for (HashMap.Entry<String, String> entry : map.entrySet()) {
							paginacaoForm += " <input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\""
									+ entry.getValue() + "\">\n";
						}
						paginacaoForm += " <input type=\"hidden\" name=\"value\" value=\"" + value + "\">\n"
								+ " <input type=\"hidden\" name=\"key\" value=\"" + key + "\">\n"
								+ "<button class=\"page-link\">" + i + "</button>\n" + "</form>\n" + "</li>\n";
					}
				}
			} else if (currentPage > 3) {
				for (int i = currentPage - 2; i <= (currentPage + 2); i++) {
					if (i == currentPage) {
						paginacaoForm += "<li class=\"page-item active\">\n" + "<span class=\"page-link active\">\n"
								+ "" + i + "\n" + "<span class=\"sr-only\">(atual)</span>\n" + "</span>\n" + "</li>\n";

					} else {
						paginacaoForm += "<li class=\"page-item\">\n" + "<form action=\""+ idForm +"\" method=\"POST\" id=\"" + idForm + "\">\n"
								+ "<input type=\"hidden\" name=\"" + page + "\" value=\"" + i + "\">\n";
						for (HashMap.Entry<String, String> entry : map.entrySet()) {
							paginacaoForm += " <input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\""
									+ entry.getValue() + "\">\n";
						}
						paginacaoForm += " <input type=\"hidden\" name=\"value\" value=\"" + value + "\">\n"
								+ " <input type=\"hidden\" name=\"key\" value=\"" + key + "\">\n"
								+ "<button class=\"page-link\">" + i + "</button>\n" + "</form>\n" + "</li>\n";
					}
				}
			} else if (totalPages < 5) {
				for (int i = 1; i <= totalPages; i++) {
					if (i == currentPage) {
						paginacaoForm += "<li class=\"page-item active\">\n" + "<span class=\"page-link active\">\n"
								+ "" + i + "\n" + "<span class=\"sr-only\">(atual)</span>\n" + "</span>\n" + "</li>\n";

					} else {
						paginacaoForm += "<li class=\"page-item\">\n" + "<form  action=\""+ idForm +"\" method=\"POST\" id=\"" + idForm + "\">\n"
								+ "<input type=\"hidden\" name=\"" + page + "\" value=\"" + i + "\">\n";
						for (HashMap.Entry<String, String> entry : map.entrySet()) {
							paginacaoForm += " <input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\""
									+ entry.getValue() + "\">\n";
						}
						paginacaoForm += " <input type=\"hidden\" name=\"value\" value=\"" + value + "\">\n"
								+ " <input type=\"hidden\" name=\"key\" value=\"" + key + "\">\n"
								+ "<button class=\"page-link\">" + i + "</button>\n" + "</form>\n" + "</li>\n";
					}
				}
			} else {
				for (int i = 1; i <= 5; i++) {
					if (i == currentPage) {
						paginacaoForm += "<li class=\"page-item active\">\n" + "<span class=\"page-link active\">\n"
								+ "" + i + "\n" + "<span class=\"sr-only\">(atual)</span>\n" + "</span>\n" + "</li>\n";

					} else {
						paginacaoForm += "<li class=\"page-item\">\n" + "<form action=\""+ idForm +"\" method=\"POST\" id=\"" + idForm + "\">\n"
								+ "<input type=\"hidden\" name=\"" + page + "\" value=\"" + i + "\">\n";
						for (HashMap.Entry<String, String> entry : map.entrySet()) {
							paginacaoForm += " <input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\""
									+ entry.getValue() + "\">\n";
						}
						paginacaoForm += " <input type=\"hidden\" name=\"value\" value=\"" + value + "\">\n"
								+ " <input type=\"hidden\" name=\"key\" value=\"" + key + "\">\n"
								+ "<button class=\"page-link\">" + i + "</button>\n" + "</form>\n" + "</li>\n";
					}
				}
			}

			paginacaoForm += "<li class=\"page-item ";
			if (currentPage == totalPages) {
				paginacaoForm += "disabled \">";
			} else {
				paginacaoForm += "\">";
			}

			paginacaoForm += "<form  action=\""+ idForm +"\" method=\"POST\" id=\"" + idForm + "\">\n" + "<input type=\"hidden\" name=\"" + page + "\" value=\""
					+ totalPages + "\">\n";
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				paginacaoForm += " <input type=\"hidden\" name=\"" + entry.getKey() + "\" value=\"" + entry.getValue()
						+ "\">\n";
			}
			paginacaoForm += " <input type=\"hidden\" name=\"value\" value=\"" + value + "\">\n"
					+ " <input type=\"hidden\" name=\"key\" value=\"" + key + "\">\n"
					+ "<button class=\"page-link\">Última página</button>\n" + "</form>\n" + "</li>\n" + "</ul>\n"
					+ "</nav>\n" + "</div>\n" + "</div>";
		}
		return paginacaoForm;
	}

}
