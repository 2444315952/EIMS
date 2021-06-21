import {
	createRouter,
	createWebHistory
} from 'vue-router'

import Login from '../components/Login.vue'
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
import AllotList from '../components/warehouse/AllotList.vue'
import AllotDetail from '../components/warehouse/AllotDetail.vue'
import BlitemList from '../components/warehouse/BlitemList.vue'
import BlitemDetails from '../components/warehouse/BlitemDetails.vue'

import PaymentList from '../components/capital/PaymentList.vue'
import Customer from '../components/basicData/Customer.vue'
import Product from '../components/basicData/Product.vue'

import dingdandetail from '../components/sell/dingdandetail.vue'
import dingdanquery from '../components/sell/dingdanquery.vue'
import sellbill from "../components/sell/SellBill.vue"
import selldetail from '../components/sell/SellDetail.vue'
import sellreturn from '../components/sell/SellReturn.vue'
import sellreturndetail from '../components/sell/SellReturnDetail.vue'

const routes = [{
		path: '/',
		redirect: '/Login'
	},
	{
		path: '/Login',
		name: "Login",
		component: Login
	},

	{
		path: '/PurchaseList',
		name: 'PurchaseList',
		component: PurchaseList
	},
	{
		path: '/Purchase/:purchId?',
		name: 'Purchase',
		component: Purchase
	},
	{
		path: '/PurchaseReturnList',
		name: 'PurchaseReturnList',
		component: PurchaseReturnList
	},
	{
		path: '/PurchaseReturn',
		name: 'PurchaseReturn',
		component: PurchaseReturn
	},
	{
		path: '/InquiryList',
		name: 'InquiryList',
		component: InquiryList
	},
	{
		path: '/Inquiry',
		name: 'Inquiry',
		component: Inquiry
	},
	{
		path: '/QuotationList',
		name: 'QuotationList',
		component: QuotationList
	},
	{
		path: '/Quotation',
		name: 'Quotation',
		component: Quotation
	},
	{
		path: '/SmartReplenish',
		name: 'SmartReplenish',
		component: SmartReplenish
	},
	//仓库管理
	{
		path: '/Allot',
		name:'Allot',
		component: AllotList
	},
	{
		path: '/AddAllot',
		name:'AddAllot',
		component: AllotDetail
	},
	{
		path: '/Storage',
		name: 'Storage',
		component: Storage
	},
	{
		path: '/AddStorage',
		name: 'AddStorage',
		component: AddStorage
	},
	{
		path: '/Odo',
		name: 'Odo',
		component: Odo
	},
	{
		path: '/AddOdo',
		name: 'AddOdo',
		component: AddOdo
	},
	{
		path: '/Blitem',
		name:'Blitem',
		component: BlitemList
	},
	{
		path: '/Addblitem',
		name:'Addblitem',
		component: BlitemDetails
	},
	//资金管理
	{
		path: '/PaymentList',
		name: 'PaymentList',
		component: PaymentList
	},
	//基础资料
	{
		path: '/Customer',
		name: 'Customer',
		component: Customer
	},
	{
		path: '/Product',
		name: 'Product',
		component: Product
	},
	{
		path: '/dingdandetail/:sellOrderId?',
		name: 'dingdandetail',
		component: dingdandetail
	},
	{
		path: '/dingdanquery',
		name: 'dingdanquery',
		component: dingdanquery
	},

	{
		path: '/sellbill',
		name: 'sellbill',
		component: sellbill
	},
	{
		path: '/selldetail/:sellId?',
		name: 'selldetail',
		component: selldetail
	},

	{
		path: '/sellreturn',
		name: 'sellreturn',
		component: sellreturn
	},
	 {
		path: '/sellreturndetail',
		name: 'sellreturndetail',
		component: sellreturndetail
	}
]

const router = createRouter({
	history: createWebHistory(),
	routes
})

export default router
