Ext.define('App.store.need.NeedStore', {
	extend : 'Ext.data.Store',
	model : 'App.model.need.NeedModel',
	storeId : 'need.NeedStore',
	pageSize : Constant.PAGESIZE,
	remoteSort: true,
	proxy : {
		type : 'ajax',
		url : basePath + '/ver/need/pageNeed',
		reader : {
			type : 'json',
			rootProperty : 'data.items',
			totalProperty : 'data.total',
			messageProperty : 'msg'
		}
	}
});