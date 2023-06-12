package gocharges.auth

import gocharges.domain.base.BaseEntity
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes=['series', 'username'])
@ToString(includes=['series', 'username'], cache=true, includeNames=true, includePackage=false)
class PersistentLogin extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1

	String series
	String username
	String token
	Date lastUsed

	static constraints = {
		series nullable: false, maxSize: 64
		token nullable: false, maxSize: 64
		username nullable: false, maxSize: 64, index:'username_idx'
		lastUsed nullable: false
	}

	static mapping = {
		table 'user_auth_token'
		id name: 'series', generator: 'assigned'
		version false
	}
}
