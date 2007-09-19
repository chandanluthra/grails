/**
 * Tests the behaviour of the redirect method
 
 * @author Graeme Rocher
 * @since 1.0
  *
 * Created: Sep 13, 2007
 * Time: 10:10:45 AM
 * 
 */
package org.codehaus.groovy.grails.web.servlet.mvc
class RedirectMethodTests extends AbstractGrailsControllerTests {

    void onSetUp() {
        gcl.parseClass('''
class RedirectController {

    def toAction = {
        redirect(action:'foo')
    }
    def toController = {
        redirect(controller:'test')
    }
    def toControllerAndAction = {
        redirect(controller:'test', action:'foo')
    }

    def toControllerWithParams = {
        redirect(controller:'test',action:'foo', params:[one:'two', two:'three'])
    }
    def toControllerWithDuplicateParams = {
        redirect(controller:'test',action:'foo', params:[one:['two','three']])
    }
    def toControllerWithDuplicateArrayParams = {
        redirect(controller:'test',action:'foo', params:[one:['two','three'] as String[]])
    }

}
class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
	}
}
        ''')
    }

    void testRedirectToAction() {
        def c = ga.getControllerClass("RedirectController").newInstance()
        webRequest.controllerName = 'redirect'
        c.toAction.call()
        assertEquals "/redirect/foo", response.redirectedUrl
    }

    void testRedirectToController() {
        def c = ga.getControllerClass("RedirectController").newInstance()
        webRequest.controllerName = 'redirect'
        c.toController.call()
        assertEquals "/test", response.redirectedUrl
    }

    void testRedirectToControllerAndAction() {
        def c = ga.getControllerClass("RedirectController").newInstance()
        webRequest.controllerName = 'redirect'
        c.toControllerAndAction.call()
        assertEquals "/test/foo", response.redirectedUrl

    }

    void testRedirectToControllerWithParams() {
        def c = ga.getControllerClass("RedirectController").newInstance()
        webRequest.controllerName = 'redirect'
        c.toControllerWithParams.call()
        assertEquals "/test/foo?one=two&two=three", response.redirectedUrl

    }

    void testRedirectToControllerWithDuplicateParams() {
        def c = ga.getControllerClass("RedirectController").newInstance()
        webRequest.controllerName = 'redirect'
        c.toControllerWithDuplicateParams.call()
        assertEquals "/test/foo?one=two&one=three", response.redirectedUrl

    }

    void testRedirectToControllerWithDuplicateArrayParams() {
        def c = ga.getControllerClass("RedirectController").newInstance()
        webRequest.controllerName = 'redirect'
        c.toControllerWithDuplicateArrayParams.call()
        assertEquals "/test/foo?one=two&one=three", response.redirectedUrl

    }
}