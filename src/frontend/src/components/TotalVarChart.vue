<template>
  <div>
    <bar-chart v-if="isChartDataAvailable" :chart-data="chartData" :options="chartOptions" />
  </div>
</template>

<script>
import { defineComponent, ref, watch, computed } from 'vue';
import { Bar } from 'vue-chartjs';
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

export default defineComponent({
  name: "FailPieChart",
  components: { BarChart: Bar },
  props: {
    authCheckList: Array
  },
  setup(props) {
    const chartData = ref({
      labels: [],
      datasets: [
        {
          label: '성공',
          data: [],
          backgroundColor: '#7bff56' // Green for success
        },
        {
          label: '실패',
          data: [],
          backgroundColor: '#ff5656' // Red for fail
        }
      ]
    });

    const chartOptions = {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            // y축의 눈금을 정수로 만듭니다.
            stepSize: 1
          }
        }
      }
    };

    const generateChartData = () => {
      const successCounts = {};
      const failCounts = {};

      props.authCheckList.forEach(item => {
        const method = translateAuthMethod(item.authMethod);
        if (item.authResult) {
          successCounts[method] = (successCounts[method] || 0) + 1;
        } else {
          failCounts[method] = (failCounts[method] || 0) + 1;
        }
      });

      const methods = Object.keys({...successCounts, ...failCounts});
      chartData.value.labels = methods;
      chartData.value.datasets[0].data = methods.map(method => successCounts[method] || 0);
      chartData.value.datasets[1].data = methods.map(method => failCounts[method] || 0);
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

    watch(() => props.authCheckList, generateChartData, {immediate: true});

    const isChartDataAvailable = computed(() => chartData.value.labels.length > 0);

    return {
      chartData: chartData.value,
      chartOptions,
      isChartDataAvailable
    };
  }
});
</script>
