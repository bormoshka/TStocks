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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bormoshka.tstocks.DAO.entities.Page;

/**
 *
 * @author 45
 */
@Controller
@RequestMapping(value = "*", method = RequestMethod.GET)
public class IndexController extends AbstractController {
	
	public IndexController(){
		super();
	}

	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return view(model);
	}
	
	@RequestMapping(value = "/page/{pageUriName}*", method = RequestMethod.GET)
	public String renderPageByUri(@PathVariable String pageUriName, ModelMap model) {
		try {
			Page page = webManager.getPage(pageUriName);
			model.addAttribute("page", page);
			model.addAttribute("title", page.getTitle());
			model.addAttribute("description", page.getDescription());
			model.addAttribute("keywords", page.getKeywords());
			
			
		} catch (Exception ex) {
			Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
			return error404(model);
		}
		return view(model);
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String sayHello(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return view(model);
	}

}
