<template>
  <table class="table table-dark">
    <thead>
    <tr>
      <!--<th>ID</th>-->
      <th>인증수단</th>
      <th>인증결과</th>
    </tr>
    </thead>
    <tbody>
    <tr class="table-light" v-for="data in authCheckList" :key="data.id">
      <!--<td>{{ data.id }}</td>-->
      <td>{{ translateAuthMethod(data.authMethod) }}</td>
      <td>{{ data.authResult ? '성공' : '실패' }}</td>
    </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: "TableComponent",
  props: {
    authCheckList: { type: Array, required: true }
  },
  methods: {
    translateAuthMethod(method) {
      const methodTranslations = {
        'homepage': '홈페이지',
        'ars': 'ARS인증',
        'sms': 'SMS인증',
        'appCard': '앱카드',
        'bioData': '생체인증',
        'samsungPay': '삼성페이'
      };
      return methodTranslations[method] || method;
    }
  }
};
</script>