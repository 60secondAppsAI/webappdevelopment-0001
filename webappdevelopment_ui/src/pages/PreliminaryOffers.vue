<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <preliminaryOffer-table
            v-if="preliminaryOffers && preliminaryOffers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:preliminaryOffers="preliminaryOffers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-preliminary-offers="getAllPreliminaryOffers"
             >

            </preliminaryOffer-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PreliminaryOfferTable from "@/components/PreliminaryOfferTable";
import PreliminaryOfferService from "../services/PreliminaryOfferService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PreliminaryOfferTable,
  },
  data() {
    return {
      preliminaryOffers: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllPreliminaryOffers(sortBy='preliminaryOfferId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PreliminaryOfferService.getAllPreliminaryOffers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.preliminaryOffers.length) {
					this.preliminaryOffers = response.data.preliminaryOffers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching preliminaryOffers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching preliminaryOffer details:", error);
      }
    },
  },
  mounted() {
    this.getAllPreliminaryOffers();
  },
  created() {
    this.$root.$on('searchQueryForPreliminaryOffersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPreliminaryOffers();
    })
  }
};
</script>
<style></style>
