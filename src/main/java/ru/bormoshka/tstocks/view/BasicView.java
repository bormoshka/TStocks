/**
 * DISCLAIMER
 * 
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 * 
 * @author jouni@vaadin.com
 * 
 */

package ru.bormoshka.tstocks.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class BasicView extends VerticalLayout implements View {

	public BasicView() {
		
	}	
	
    @Override
    public void enter(ViewChangeEvent event) {
        setSizeFull();
        addStyleName("timeline");


    }

}
