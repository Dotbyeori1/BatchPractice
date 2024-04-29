<template>
  <CsvFileUploadComponent @file-uploaded="onFileUploaded"/>
  <div style="margin-bottom: 20px;">
    <button @click="setVisibleChart('success')">버튼1</button>
    <button @click="setVisibleChart('fail')">버튼2</button>
    <button @click="setVisibleChart('totalVar')">버튼3</button>
  </div>
  <div class="dashboard-grid">
    <SuccessPieChart v-if="visibleChart === 'success' || visibleChart === 'all'" :authCheckList="authCheckList" />
    <FailPieChart v-if="visibleChart === 'fail' || visibleChart === 'all'" :authCheckList="authCheckList" />
    <TotalVarChart v-if="visibleChart === 'totalVar' || visibleChart === 'all'" :authCheckList="authCheckList" />
    <TableComponent :authCheckList="authCheckList" />
  </div>
</template>

<script>
import CsvFileUploadComponent from "@/components/CsvFileUploadComponent.vue";
import FailPieChart from "@/components/FailPieChart.vue";
import SuccessPieChart from "@/components/SuccessPieChart.vue";
import TableComponent from "@/components/TableComponent.vue";
import TotalVarChart from "@/components/TotalVarChart.vue";
import ApiService from "@/services/ApiService";

export default {
  name: "DashBoardComponent",
  components: { TableComponent, SuccessPieChart, FailPieChart, TotalVarChart, CsvFileUploadComponent },
  data() {
    return {
      authCheckList: [],
      visibleChart: 'all'
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await ApiService.getAuthCheckList();
        this.authCheckList = response.data.data;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    setVisibleChart(chartName) {
      this.visibleChart = chartName;
    },
    onFileUploaded(responseData) {
      console.log("File uploaded successfully:", responseData);
      this.fetchData();
    }
  }
}
</script>

<style>
.dashboard-grid {
  display: grid;
  grid-template-columns: 1fr 1fr; /* 2열 구조 */
  grid-gap: 20px; /* 그리드 사이의 간격 */
}
</style>