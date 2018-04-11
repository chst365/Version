Ext.define('App.store.version.VersionStore', {
	extend : 'Ext.data.Store',
	model : 'App.model.version.VersionModel',
	storeId : 'version.VersionStore',
	pageSize : Constant.PAGESIZE,
	remoteSort: true,
	proxy : {
		type : 'ajax',
		url : basePath + '/ver/version/pageVersion',
		reader : {
			type : 'json',
			rootProperty : 'data.items',
			totalProperty : 'data.total',
			messageProperty : 'msg'
		}
	}
});