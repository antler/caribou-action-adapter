package com.instrument.triface.action;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import clojure.lang.IPersistentList;
import clojure.lang.IPersistentMap;
import clojure.lang.PersistentHashMap;

import com.instrument.triface.IObjectFactory;
import com.instrument.triface.JRubyObjectFactory;
import com.instrument.triface.JythonObjectFactory;
import com.instrument.triface.action.ITrifaceAction.MapType;

/**
 * Action converted map tests
 * 
 * @author feigner
 *
 */
public class TrifaceActionGetConvertedMapTest extends ActionTest{
	
	protected IObjectFactory objectFactory;
	protected ITrifaceAction action;
	
	@Test
	public void testJRubyToClojureConvertedMap()
	{
		objectFactory = getJRubyObjectFactory("NativeTypesAction");
		action = (ITrifaceAction) objectFactory.createObject();
		action.execute();
		testToClojureConvertedMap();
		
		// reinit action
		action = (ITrifaceAction) objectFactory.createObject();
		action.setMap(PersistentHashMap.EMPTY);
		action.execute();
		testToClojureConvertedMap();
	}
	
	@Test
	public void testJythonToClojureConvertedMap()
	{
		objectFactory = getJythonObjectFactory("NativeTypesAction");
    	action = (ITrifaceAction) objectFactory.createObject();
		action.execute();
		testToClojureConvertedMap();
		
		// reinit action
		action = (ITrifaceAction) objectFactory.createObject();
		action.setMap(PersistentHashMap.EMPTY);
		action.execute();
		testToClojureConvertedMap();
	}	
	
	public void testToClojureConvertedMap()
	{	
		Map<Object, Object> m = action.getConvertedMap(MapType.CLOJURE);
		assertTrue(m instanceof IPersistentMap);
		
		// check converted types
		assertTrue(m.get("map") instanceof IPersistentMap);
		assertTrue(m.get("list") instanceof IPersistentList);
		
		// check non-converted types
		assertTrue(m.get("string") instanceof String);
	}

}
