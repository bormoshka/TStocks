package ru.bormoshka.tstocks.view;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import ru.bormoshka.tstocks.common.SpringContextHelper;
import ru.bormoshka.tstocks.controller.StockController;

@Theme("dusty")
@SuppressWarnings("serial")
public class MainScreen extends UI {

	protected final SpringContextHelper helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
	protected final VerticalLayout layout = new VerticalLayout();
	protected final HorizontalLayout content = new HorizontalLayout();
	
	CssLayout root = new CssLayout();
	CssLayout menu = new CssLayout();
	
	private Navigator nav;
	
	HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();

	HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>() {
		{
			put("/dashboard", BasicView.class);
		}
	};

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = true,
			ui = MainScreen.class,
			widgetset = "ru.bormoshka.tstocks.view.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		setLocale(Locale.US);

		setContent(root);

		layout.setSizeFull();

		initToolbar();

		layout.addComponent(content);

		content.setSizeFull();
		setContent(layout);
	}

	private void initToolbar() {
		root.addComponent(new HorizontalLayout() {
			{
				setSizeFull();
				addStyleName("main-view");
				addComponent(new VerticalLayout() {
					{
						addStyleName("sidebar");
						setWidth(null);
						setHeight("100%");

						addComponent(new CssLayout() {
							{
								addStyleName("branding");
								Label logo = new Label(
										"<span>tStocks</span> Dashboard",
										ContentMode.HTML);
								logo.setSizeUndefined();
								addComponent(logo);
							}
						});
						addComponent(menu);
						setExpandRatio(menu, 1);
					}
				});
				menu.removeAllComponents();

				for (final String view : new String[]{"dashboard", "sales",
					"transactions"}) {
					Button b = new NativeButton(view.substring(0, 1).toUpperCase()
							+ view.substring(1).replace('-', ' '));
					b.addStyleName("icon-" + view);
					b.addClickListener(new Button.ClickListener() {
						@Override
						public void buttonClick(Button.ClickEvent event) {
							clearMenuSelection();
							event.getButton().addStyleName("selected");
							if (!nav.getState().equals("/" + view)) {
								nav.navigateTo("/" + view);
							}
						}
					});

					menu.addComponent(b);

					viewNameToMenuButton.put("/" + view, b);
				}
				// Content
				addComponent(content);
				content.setSizeFull();
				content.addStyleName("view-content");
				setExpandRatio(content, 1);
			}
		});
	}

	private void attachStockController(AbstractLayout layout) {
		Button btnViewUnits = new NativeButton("View Units");
		Button btnAddUnit = new NativeButton("ADD");

		layout.addComponent(btnViewUnits);
		layout.addComponent(btnAddUnit);

		StockController stock = (StockController) helper.getBean("stockController");

		layout.addComponent(btnViewUnits);
		layout.addComponent(btnAddUnit);

		btnViewUnits.addClickListener(new ViewButtonClickListener(content, stock));
		btnAddUnit.addClickListener(new AddButtonClickListener(content, stock));
	}

	private void clearMenuSelection() {
		for (Iterator<Component> it = menu.getComponentIterator(); it.hasNext();) {
			Component next = it.next();
			if (next instanceof NativeButton) {
				next.removeStyleName("selected");
			} else if (next instanceof DragAndDropWrapper) {
				// Wow, this is ugly (even uglier than the rest of the code)
				((DragAndDropWrapper) next).iterator().next()
						.removeStyleName("selected");
			}
		}
	}
}
