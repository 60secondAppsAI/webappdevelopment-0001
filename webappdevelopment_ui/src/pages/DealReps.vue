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
            <dealRep-table
            v-if="dealReps && dealReps.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:dealReps="dealReps"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-deal-reps="getAllDealReps"
             >

            </dealRep-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DealRepTable from "@/components/DealRepTable";
import DealRepService from "../services/DealRepService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DealRepTable,
  },
  data() {
    return {
      dealReps: [],
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
    async getAllDealReps(sortBy='dealRepId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DealRepService.getAllDealReps(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.dealReps.length) {
					this.dealReps = response.data.dealReps;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching dealReps:", error);
        }
        
      } catch (error) {
        console.error("Error fetching dealRep details:", error);
      }
    },
  },
  mounted() {
    this.getAllDealReps();
  },
  created() {
    this.$root.$on('searchQueryForDealRepsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDealReps();
    })
  }
};
</script>
<style></style>
