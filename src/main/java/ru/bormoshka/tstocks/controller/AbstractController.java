/*
 * Copyright (C) 2013 45
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.bormoshka.tstocks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import ru.bormoshka.tstocks.DAO.WebManager;
import ru.bormoshka.tstocks.DAO.entities.Page;
import ru.bormoshka.tstocks.common.SpringContextHelper;
import ru.bormoshka.tstocks.local.L;

/**
 *
 * @author 45
 */
@Controller
public class AbstractController {

	protected String resourcePrefix = "/TStocks/";
	protected String indexLocation = "/TStocks/";
	protected String title;
	protected String description;
	protected String keywords;
	protected String layout = "index";

	protected Set<String> styles;
	protected Set<String> scripts;
	
	protected Map<String, String> menu;

	protected Map<String, String> content = new HashMap<>();

	protected String template = "index";
	protected String view = "index";

	protected LocaleLabelWrapper label = new LocaleLabelWrapper();
	
	@Autowired
	ServletContext context;
	
	protected SpringContextHelper helper;
	protected WebManager webManager;

	public AbstractController() {			
	}
	
	@PostConstruct
	protected void initController() {
		helper = new SpringContextHelper(context);
				
		title = L.getWeb("default_title");
		description = L.getWeb("default_description");
		keywords = L.getWeb("default_keywords");
		
		try {
			webManager = (WebManager) helper.getBean("webManager");
			List<Page> pages = webManager.getAllPages();
			menu = new HashMap<>();
			for (Page page : pages) {
				menu.put(page.getUri(), page.getLinkName());
			}
		} catch (Exception ex) {
			Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	protected String view(ModelMap model) {
		model.addAttribute("resourcePrefix", resourcePrefix);
		model.addAttribute("title", title);
		model.addAttribute("description", description);
		model.addAttribute("keywords", keywords);
		model.addAttribute("indexLocation", indexLocation);
		model.addAttribute("view", view);
		model.addAttribute("menu", menu);
		model.addAttribute("label", label);
		model.addAttribute("content", content);
		return template;
	}
	
	protected String error404(ModelMap model) {
		view(model);
		return "error404";
	}

	class LocaleLabelWrapper {

		public String get(String name) {
			return L.getWeb(name);
		}
	}
}
