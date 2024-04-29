<template>
  <div>
    <pie-chart v-if="isChartDataAvailable" :chart-data="chartData" :options="chartOptions" />
  </div>
</template>

<script>
import { defineComponent, ref, watch, computed } from 'vue';
import { Pie } from 'vue-chartjs';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

export default defineComponent({
  name: "SuccessPieChart",
  components: { PieChart: Pie },
  props: {
    authCheckList: Array
  },
  setup(props) {
    const chartData = ref({
      labels: [],
      datasets: [
        {
          data: [],
          backgroundColor: ['#FF6384', '#36A2EB', '#7bff56', '#ff5656', '#FFCE56', '#FF1234']
        }
      ]
    });

    const chartOptions = {
      responsive: true,
      maintainAspectRatio: false
    };

    const generateChartData = () => {
      const methodCounts = props.authCheckList.reduce((acc, item) => {
        if (item.authResult) {
          const method = translateAuthMethod(item.authMethod);
          acc[method] = (acc[method] || 0) + 1;
        }
        return acc;
      }, {});

      chartData.value.labels = Object.keys(methodCounts);
      chartData.value.datasets[0].data = Object.values(methodCounts);
    };

    const translateAuthMethod = (method) => {
      const methodTranslations = {
        'homepage': '홈페이지',
        'ars': 'ARS인증',
        'sms': 'SMS인증',
        'appCard': '앱카드',
        'bioData': '생체인증',
        'samsungPay': '삼성페이'
      };
      return methodTranslations[method] || method;
    };

    watch(() => props.authCheckList, generateChartData, { immediate: true });

    const isChartDataAvailable = computed(() => chartData.value.labels.length > 0);

    return {
      chartData: chartData.value,
      chartOptions,
      isChartDataAvailable
    };
  }
});
</script>
