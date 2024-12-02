import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Websites from  '@/pages/Websites.vue';
import WebsiteDetail from  '@/pages/WebsiteDetail.vue';
import Customers from  '@/pages/Customers.vue';
import CustomerDetail from  '@/pages/CustomerDetail.vue';
import CustomerSubmissions from  '@/pages/CustomerSubmissions.vue';
import CustomerSubmissionDetail from  '@/pages/CustomerSubmissionDetail.vue';
import UserAccounts from  '@/pages/UserAccounts.vue';
import UserAccountDetail from  '@/pages/UserAccountDetail.vue';
import Deals from  '@/pages/Deals.vue';
import DealDetail from  '@/pages/DealDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import DealDashboards from  '@/pages/DealDashboards.vue';
import DealDashboardDetail from  '@/pages/DealDashboardDetail.vue';
import DealReps from  '@/pages/DealReps.vue';
import DealRepDetail from  '@/pages/DealRepDetail.vue';
import PreliminaryOffers from  '@/pages/PreliminaryOffers.vue';
import PreliminaryOfferDetail from  '@/pages/PreliminaryOfferDetail.vue';
import ShippingBoxs from  '@/pages/ShippingBoxs.vue';
import ShippingBoxDetail from  '@/pages/ShippingBoxDetail.vue';
import ItemHandlings from  '@/pages/ItemHandlings.vue';
import ItemHandlingDetail from  '@/pages/ItemHandlingDetail.vue';
import Valuations from  '@/pages/Valuations.vue';
import ValuationDetail from  '@/pages/ValuationDetail.vue';
import ValuationTeams from  '@/pages/ValuationTeams.vue';
import ValuationTeamDetail from  '@/pages/ValuationTeamDetail.vue';
import Offers from  '@/pages/Offers.vue';
import OfferDetail from  '@/pages/OfferDetail.vue';
import Agreements from  '@/pages/Agreements.vue';
import AgreementDetail from  '@/pages/AgreementDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Storages from  '@/pages/Storages.vue';
import StorageDetail from  '@/pages/StorageDetail.vue';
import Repayments from  '@/pages/Repayments.vue';
import RepaymentDetail from  '@/pages/RepaymentDetail.vue';
import LoanRepayments from  '@/pages/LoanRepayments.vue';
import LoanRepaymentDetail from  '@/pages/LoanRepaymentDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/websites',
																			  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/websites',
		name: 'Websites',
		layout: DefaultLayout,
		component: Websites,
	},
	{
	    path: '/website/:websiteId', 
	    name: 'WebsiteDetail',
		layout: DefaultLayout,
	    component: WebsiteDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/customers',
		name: 'Customers',
		layout: DefaultLayout,
		component: Customers,
	},
	{
	    path: '/customer/:customerId', 
	    name: 'CustomerDetail',
		layout: DefaultLayout,
	    component: CustomerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/customerSubmissions',
		name: 'CustomerSubmissions',
		layout: DefaultLayout,
		component: CustomerSubmissions,
	},
	{
	    path: '/customerSubmission/:customerSubmissionId', 
	    name: 'CustomerSubmissionDetail',
		layout: DefaultLayout,
	    component: CustomerSubmissionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/userAccounts',
		name: 'UserAccounts',
		layout: DefaultLayout,
		component: UserAccounts,
	},
	{
	    path: '/userAccount/:userAccountId', 
	    name: 'UserAccountDetail',
		layout: DefaultLayout,
	    component: UserAccountDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/deals',
		name: 'Deals',
		layout: DefaultLayout,
		component: Deals,
	},
	{
	    path: '/deal/:dealId', 
	    name: 'DealDetail',
		layout: DefaultLayout,
	    component: DealDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/dealDashboards',
		name: 'DealDashboards',
		layout: DefaultLayout,
		component: DealDashboards,
	},
	{
	    path: '/dealDashboard/:dealDashboardId', 
	    name: 'DealDashboardDetail',
		layout: DefaultLayout,
	    component: DealDashboardDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/dealReps',
		name: 'DealReps',
		layout: DefaultLayout,
		component: DealReps,
	},
	{
	    path: '/dealRep/:dealRepId', 
	    name: 'DealRepDetail',
		layout: DefaultLayout,
	    component: DealRepDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/preliminaryOffers',
		name: 'PreliminaryOffers',
		layout: DefaultLayout,
		component: PreliminaryOffers,
	},
	{
	    path: '/preliminaryOffer/:preliminaryOfferId', 
	    name: 'PreliminaryOfferDetail',
		layout: DefaultLayout,
	    component: PreliminaryOfferDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/shippingBoxs',
		name: 'ShippingBoxs',
		layout: DefaultLayout,
		component: ShippingBoxs,
	},
	{
	    path: '/shippingBox/:shippingBoxId', 
	    name: 'ShippingBoxDetail',
		layout: DefaultLayout,
	    component: ShippingBoxDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/itemHandlings',
		name: 'ItemHandlings',
		layout: DefaultLayout,
		component: ItemHandlings,
	},
	{
	    path: '/itemHandling/:itemHandlingId', 
	    name: 'ItemHandlingDetail',
		layout: DefaultLayout,
	    component: ItemHandlingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/valuations',
		name: 'Valuations',
		layout: DefaultLayout,
		component: Valuations,
	},
	{
	    path: '/valuation/:valuationId', 
	    name: 'ValuationDetail',
		layout: DefaultLayout,
	    component: ValuationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/valuationTeams',
		name: 'ValuationTeams',
		layout: DefaultLayout,
		component: ValuationTeams,
	},
	{
	    path: '/valuationTeam/:valuationTeamId', 
	    name: 'ValuationTeamDetail',
		layout: DefaultLayout,
	    component: ValuationTeamDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/offers',
		name: 'Offers',
		layout: DefaultLayout,
		component: Offers,
	},
	{
	    path: '/offer/:offerId', 
	    name: 'OfferDetail',
		layout: DefaultLayout,
	    component: OfferDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/agreements',
		name: 'Agreements',
		layout: DefaultLayout,
		component: Agreements,
	},
	{
	    path: '/agreement/:agreementId', 
	    name: 'AgreementDetail',
		layout: DefaultLayout,
	    component: AgreementDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/storages',
		name: 'Storages',
		layout: DefaultLayout,
		component: Storages,
	},
	{
	    path: '/storage/:storageId', 
	    name: 'StorageDetail',
		layout: DefaultLayout,
	    component: StorageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/repayments',
		name: 'Repayments',
		layout: DefaultLayout,
		component: Repayments,
	},
	{
	    path: '/repayment/:repaymentId', 
	    name: 'RepaymentDetail',
		layout: DefaultLayout,
	    component: RepaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loanRepayments',
		name: 'LoanRepayments',
		layout: DefaultLayout,
		component: LoanRepayments,
	},
	{
	    path: '/loanRepayment/:loanRepaymentId', 
	    name: 'LoanRepaymentDetail',
		layout: DefaultLayout,
	    component: LoanRepaymentDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
