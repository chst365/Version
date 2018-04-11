Ext.define('App.model.version.VersionModel', {
	extend : 'Ext.data.Model',
	fields : [ 'id','versionNumber','systemId','predictStartTime','predictPublishTime','developmentTime','uptime','createTime','modifyTime','status' ]
});