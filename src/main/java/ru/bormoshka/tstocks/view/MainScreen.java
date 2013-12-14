package ru.bormoshka.tstocks.view;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import ru.bormoshka.tstocks.common.SpringContextHelper;
import ru.bormoshka.tstocks.local.L;

@Theme("dusty")
@SuppressWarnings("serial")
public class MainScreen extends UI {

	protected final static SpringContextHelper helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
	protected final HorizontalLayout content = new HorizontalLayout();

	CssLayout root = new CssLayout();
	CssLayout menu = new CssLayout();

	private Navigator nav;

	protected final HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();
	protected String[] enabledSidebarButtons;
	
	HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>() {
		{
			put("/actual_data", DataView.class);
			put("/stocking", StockingView.class);
			put("/transactions", TransactionView.class);
			put("/lists", ListsView.class);
			put("/dashboard", BasicView.class);
		}
	};

	@WebServlet(value = {"/app/*", "/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false,
			ui = MainScreen.class
//			widgetset = "ru.bormoshka.tstocks.view.AppWidgetSet"
		)
	public static class Servlet extends VaadinServlet {
		
	}

	@Override
	protected void init(VaadinRequest request) {

		setLocale(Locale.US);
		menu.addStyleName("menu");
		setContent(root);

		root.setSizeFull();
		initNavigation();
		initToolbar();
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

						addComponent(new HorizontalLayout() {
							{								
								Label logoSlogan = new Label(
										"tStocks <span>Dashboard</span>",
										ContentMode.HTML);
								logoSlogan.setSizeUndefined();								
								Image logoPic = new Image(null, 
										new	ThemeResource("img/dusty-app-logo.png"));
								logoPic.setStyleName("app-logo");								
								addStyleName("branding");
								addComponent(logoPic);
								addComponent(logoSlogan);
								
							}
						});
						addComponent(menu);
						setExpandRatio(menu, 1);
					}
				});
				menu.removeAllComponents();

				for (final String view : enabledSidebarButtons) {
					Button b = new NativeButton(L.get("sidebar_" + view));
					b.addStyleName("icon-" + view);
					b.addClickListener(new Button.ClickListener() {
						@Override
						public void buttonClick(Button.ClickEvent event) {
							//clearMenuSelection();
							//event.getButton().addStyleName("selected");
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

	private void clearMenuSelection() {
		for (Iterator<Component> it = menu.iterator(); it.hasNext();) {
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

	private void initNavigation() {
		this.enabledSidebarButtons = new String[]{
			"actual_data",
			"stocking",
			"transactions",
			"lists"};
		nav = new Navigator(this, content);
		
		for (String route : routes.keySet()) {
			nav.addView(route, routes.get(route));
		}
		String uri = Page.getCurrent().getUriFragment();
		if (uri != null && uri.startsWith("!")) {
			uri = uri.substring(1);
		}
		if (uri == null || uri.equals("") || uri.equals("/")) {
			nav.navigateTo("/dashboard");			
		} else if (viewNameToMenuButton.containsKey(uri)) {
			nav.navigateTo(uri);			
		}
		nav.addViewChangeListener(new ViewChangeListener() {
			@Override
			public void afterViewChange(ViewChangeListener.ViewChangeEvent event) {
				clearMenuSelection();				
				Button b = viewNameToMenuButton.get(event.getViewName());
				if(b != null) {
					b.addStyleName("selected");
				}
			}

			@Override
			public boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
				return true;
			}
		});
	}
	public static SpringContextHelper getSpringHelper() {
		return helper;
	}
}
