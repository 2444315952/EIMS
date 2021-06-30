<template>
  <div id="app">
	<el-container>
		  <el-header v-if="$route.name!== 'Login'" style="height: 55px;background-color: #B3C0D1;color: #333;line-height: 60px;" >
			<el-dropdown style="float: right;">
				<el-button icon="el-icon-user-solid" circle id="content" style="float: right;margin-top: 6px;"></el-button> 
			  <template #dropdown>
			    <el-dropdown-menu>
			      <el-dropdown-item>石松炼</el-dropdown-item>
			      <el-dropdown-item>退出登录</el-dropdown-item>
			    </el-dropdown-menu>
			  </template>
			</el-dropdown> 
			  
		  </el-header>
		  <el-container>
		  	<el-aside v-if="$route.name!== 'Login'" width="200px" style="text-align: center;">
		  		 <el-menu
		  		  default-active="2"
		  		  class="el-menu-vertical-demo"
		  		  @open="handleOpen"
		  		  @close="handleClose">
		  		  <el-submenu index="1">
		  			<template #title>
		  			  <span>采购管理</span>
		  			</template>
		  			<el-menu-item-group>	
		  				<el-menu-item index="1-1"><router-link :to="{name:'PurchaseList'}">采购单</router-link></el-menu-item>
		  				<el-menu-item index="1-2"><router-link :to="{name:'SmartReplenish'}">智能补货</router-link></el-menu-item>
		  				<el-menu-item index="1-3"><router-link :to="{name:'PurchaseReturnList'}">采购退货</router-link></el-menu-item>
		  				<el-menu-item index="1-4"><router-link :to="{name:'InquiryList'}">询价单</router-link></el-menu-item>
		  				<el-menu-item index="1-5"><router-link :to="{name:'QuotationList'}">报价单</router-link></el-menu-item>
		  			</el-menu-item-group>
		  		  </el-submenu>
		  		  <el-submenu index="2">
		  			<template #title>
		  			  <span>销售管理</span>
		  			</template>
		  			<el-menu-item-group>	
					<el-menu-item index="2-1"><router-link :to="{name:'dingdanquery'}">销售订单</router-link></el-menu-item>
					<el-menu-item index="2-2"><router-link :to="{name:'dingdandetail'}">销售订单详情</router-link></el-menu-item>
		  				<el-menu-item index="2-3"><router-link :to="{name:'sellbill'}">销售单</router-link></el-menu-item>
		  				<el-menu-item index="2-4"><router-link :to="{name:'selldetail'}">销售单详情</router-link></el-menu-item>
						<el-menu-item index="2-4"><router-link :to="{name:'sellaccounts'}">往来账务</router-link></el-menu-item>
		  				<el-menu-item index="2-5"><router-link :to="{name:'sellreturn'}">销售退货</router-link></el-menu-item>
		  			</el-menu-item-group>
		  		  </el-submenu>
		  		  <el-submenu index="3">
		  			<template #title>
		  			  <span>仓库管理</span>
		  			</template>
		  			<el-menu-item-group>	
		  				<el-menu-item index="3-1"><router-link :to="{name:'Storage'}">入库单</router-link></el-menu-item>
		  				<el-menu-item index="3-2"><router-link :to="{name:'Odo'}">出库单</router-link></el-menu-item>
		  				<el-menu-item index="3-3"><router-link :to="{name:'Allot'}">库存调拨</router-link></el-menu-item>
		  				<el-menu-item index="3-4"><router-link :to="{name:'Blitem'}">库存盘点</router-link></el-menu-item>
		  			</el-menu-item-group>
		  		  </el-submenu>
		  		  <el-submenu index="4">
		  			<template #title>
		  			  <span>资金管理</span>
		  			</template>
		  			<el-menu-item-group>	
		  				<el-menu-item index="4-1"><router-link :to="{name:'PaymentList'}">付款单</router-link></el-menu-item>
		  				<el-menu-item index="4-2">收款单</el-menu-item>
		  				<el-menu-item index="4-3">预付退款</el-menu-item>
		  				<el-menu-item index="4-4">预收退款</el-menu-item>
		  			</el-menu-item-group>
		  		  </el-submenu>
		  		  <el-submenu index="5">
		  			<template #title>
		  			  <span>基础资料</span>
		  			</template>
		  			<el-menu-item-group>	
		  				<el-menu-item index="5-1"><router-link :to="{name:'Product'}">产品</router-link></el-menu-item>
		  				<el-menu-item index="5-2"><router-link :to="{name:'Customer'}">客户</router-link></el-menu-item>
		  				<el-menu-item index="5-3">供应商</el-menu-item>
						<el-menu-item index="5-2"><router-link :to="{name:'moneyaccount'}">资金账户</router-link></el-menu-item>
		  			</el-menu-item-group>
		  		  </el-submenu>
		  		</el-menu>
		  	</el-aside>
		  	<el-container>
		  	  <el-main><router-view></router-view></el-main>
		  	</el-container>
		  </el-container>
	</el-container>
  </div>
 
</template>

<script>

export default {
  name: 'App'
}
</script>
