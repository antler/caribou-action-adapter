package com.instrument.triface.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import clojure.lang.PersistentHashMap;

import com.instrument.triface.IObjectFactory;

/**
 * Basic action tests.
 * 
 * @author feigner
 *
 */
public class BasicActionTest extends ActionTest {

	protected IObjectFactory objectFactory;
	protected ITrifaceAction action;
	
	@Test
	public void JRubyActionTest()
	{
		objectFactory = getJRubyObjectFactory("DummyAction");
		action = (ITrifaceAction) objectFactory.createObject();
		
		basicMapTest(action);
		basicClojureMapTest(action);
	}
	
	@Test
	public void JythonActionTest()
	{
		objectFactory = getJythonObjectFactory("DummyAction");
    	action = (ITrifaceAction) objectFactory.createObject();
		
		basicMapTest(action);
		basicClojureMapTest(action);
	}	
	
	@Test
	public void JSActionTest()
	{
		objectFactory = getJSObjectFactory("DummyAction");
    	action = (ITrifaceAction) objectFactory.createObject();
		
		basicMapTest(action);
		basicClojureMapTest(action);
	}	
	
	/**
	 * Ensure that without setting a map, actions return a map interface
	 * 
	 * @param action the action to test
	 */
	public void basicMapTest(ITrifaceAction action)
	{
		assertNotNull(action.getMap());
		assertTrue(action.getMap().isEmpty());
		assertTrue(action.getMap() instanceof Map);
	}
	
	/**
	 * Ensure that setting a custom map returns the same
	 * instance
	 * 
	 * @param action the action to test
	 */
	public void basicClojureMapTest(ITrifaceAction action)
	{
		PersistentHashMap cljMap = PersistentHashMap.EMPTY;
		cljMap = (PersistentHashMap) cljMap.assoc("foo", "bar");
		action.setMap(cljMap);

		assertFalse(action.getMap().isEmpty());
		assertTrue(action.getMap().containsKey("foo"));
		assertEquals("bar", action.getMap().get("foo"));
		assertTrue(action.getMap() instanceof PersistentHashMap);
	}	

}
