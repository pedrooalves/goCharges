

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'gocharges.auth.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'gocharges.auth.UserRole'
grails.plugin.springsecurity.authority.className = 'gocharges.auth.Role'

grails.plugin.springsecurity.auth.loginFormUrl = '/user/login'
grails.plugin.springsecurity.logout.afterLogoutUrl = '/user/logout'
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/dashboard/index'
grails.plugin.springsecurity.failureHandler.defaultFailureUrl = '/user/error'
grails.plugin.springsecurity.successHandler.alwaysUseDefault = false
grails.plugin.springsecurity.dao.hideUserNotFoundExceptions = false
grails.plugin.springsecurity.adh.errorPage="/logoff"
grails.plugin.springsecurity.password.algorithm = 'bcrypt'

grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"

grails.plugin.springsecurity.interceptUrlMap = [
		[pattern: '/customer/index',               access: ['ROLE_ADMIN']],
		[pattern: '/customer/create',               access: ['permitAll']],
		[pattern: '/paymentReceipt/**',               access: ['permitAll']],
		[pattern: '/payer/**',               access: ['ROLE_ADMIN', 'ROLE_USER']],
		[pattern: '/payment/**',               access: ['ROLE_ADMIN', 'ROLE_USER']],
		[pattern: '/user/myAccount',               access: ['ROLE_ADMIN', 'ROLE_USER']],
		[pattern: '/**',          access: ['IS_AUTHENTICATED_ANONYMOUSLY']],
]

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'gocharges.auth.PersistentLogin'
grails.plugin.springsecurity.rememberMe.cookieName = 'gocharges_remember_me'
grails.plugin.springsecurity.rememberMe.alwaysRemember = true
grails.plugin.springsecurity.rememberMe.persistent = true

