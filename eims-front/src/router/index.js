import {createRouter,createWebHistory} from 'vue-router'

import PurchaseList from '../components/purchase/PurchaseList.vue'
import Purchase from '../components/purchase/Purchase.vue' 
import PurchaseReturnList from '../components/purchase/PurchaseReturnList.vue'
import PurchaseReturn from '../components/purchase/PurchaseReturn.vue'
import InquiryList from '../components/purchase/InquiryList.vue'
import Inquiry from '../components/purchase/Inquiry.vue'
import QuotationList from '../components/purchase/QuotationList.vue'
import Quotation from '../components/purchase/Quotation.vue'
import SmartReplenish from '../components/purchase/SmartReplenish.vue'

import Storage from '../components/warehouse/StorageList.vue'
import AddStorage from '../components/warehouse/StorageDetails.vue'
import Odo from '../components/warehouse/OdoList.vue'
import AddOdo from '../components/warehouse/OdoDetails.vue'

import PaymentList from '../components/capital/PaymentList.vue'

import Customer from '../components/basicData/Customer.vue'
import Product from '../components/basicData/Product.vue'

const routes = [
	{
		path:'/',
		component:PurchaseList
	},
	{
		path:'/PurchaseList',
		name:'PurchaseList',
		component:PurchaseList
	},
	{
		path:'/Purchase/:purchId?',
		name:'Purchase',
		component:Purchase
	},
	{
		path:'/PurchaseReturnList',
		name:'PurchaseReturnList',
		component:PurchaseReturnList
	},
	{
		path:'/PurchaseReturn',
		name:'PurchaseReturn',
		component:PurchaseReturn
	},
	{
		path:'/InquiryList',
		name:'InquiryList',
		component:InquiryList
	},
	{
		path:'/Inquiry',
		name:'Inquiry',
		component:Inquiry
	},
	{
		path:'/QuotationList',
		name:'QuotationList',
		component:QuotationList
	},
	{
		path:'/Quotation',
		name:'Quotation',
		component:Quotation
	},
	{
		path:'/SmartReplenish',
		name:'SmartReplenish',
		component:SmartReplenish
	},
	//仓库管理
	{
		path:'/Storage',
		name:'Storage',
		component:Storage
	},
	{
		path:'/AddStorage',
		name:'AddStorage',
		component:AddStorage
	},
	{
		path:'/Odo',
		name:'Odo',
		component:Odo
	},
	{
		path:'/AddOdo',
		name:'AddOdo',
		component:AddOdo
	},
	//资金管理
	{
		path:'/PaymentList',
		name:'PaymentList',
		component:PaymentList
	},
	//基础资料
	{
		path:'/Customer',
		name:'Customer',
		component:Customer
	},
	{
		path:'/Product',
		name:'Product',
		component:Product
	}
]

const router = createRouter({
	history:createWebHistory(),
	routes
})

export default router