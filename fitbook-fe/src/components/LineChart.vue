<template>
  <div>
    <Line
      :chart-options="chartOptionsCalories"
      :chart-data="chartDataCalories"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    >
    </Line>
  </div>
  <div class="mt-10">
    <Line
      :chart-options="chartOptionsWeight"
      :chart-data="chartDataWeight"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    >
    </Line>
  </div>
  <div class="mt-10">
    <Line
      :chart-options="chartOptionsBmi"
      :chart-data="chartDataBmi"
      :chart-id="chartId"
      :dataset-id-key="datasetIdKey"
      :plugins="plugins"
      :css-classes="cssClasses"
      :styles="styles"
      :width="width"
      :height="height"
    >
    </Line>
  </div>
</template>

<script>
import { Line } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  LinearScale,
  PointElement,
  CategoryScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  LineElement,
  LinearScale,
  PointElement,
  CategoryScale
);

export default {
  components: {
    Line,
  },
  props: {
    chartId: {
      type: String,
      default: "bar-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 200,
    },
    height: {
      type: Number,
      default: 200,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      chartDataWeight: {
        labels: [],
        datasets: [
          {
            label: "Weight",
            backgroundColor: "#84B7F5",
            data: [],
          },
        ],
      },
      chartOptionsWeight: {
        responsive: true,
        maintainAspectRatio: false,
      },
      chartDataCalories: {
        labels: [],
        datasets: [
          {
            label: "Calories",
            backgroundColor: "#B7F584",
            data: [],
          },
        ],
      },
      chartOptionsCalories: {
        responsive: true,
        maintainAspectRatio: false,
      },
      chartOptionsBmi: {
        responsive: true,
        maintainAspectRatio: false,
      },
      chartDataBmi: {
        labels: [],
        datasets: [
          {
            label: "BMI",
            backgroundColor: "#F584B7",
            data: [],
          },
        ],
      },
    };
  },
  methods: {
    async getClientId() {
      const response = await fetch(
        `http://localhost:8081/api/client/user/${this.$store.getters["auth/userId"]}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        let responseData = await response.json();
        return responseData.id;
      } else {
        console.log("error getting user data");
      }
    },
    async getProgresses(clientId) {
      const response = await fetch(
        `http://localhost:8081/api/client/${clientId}/progress`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        let responseData = await response.json();

        return responseData;
      } else {
        console.log("error getting progresses");
      }
    },
  },
  async mounted() {
    const clientId = await this.getClientId();
    const progresses = await this.getProgresses(clientId);
    this.progresses = progresses;
    let weightArr = [];
    let bmiArr = [];
    let caloriesArr = [];
    let labelsArr = [];
    for (const progress of this.progresses) {
      weightArr.push(progress.weight);
      bmiArr.push(progress.bmi);
      caloriesArr.push(progress.caloriesBurned);
      labelsArr.push(progress.createdTimestamp.substring(0, 10));
    }
    this.chartDataWeight.datasets[0].data = weightArr;
    this.chartDataCalories.datasets[0].data = caloriesArr;
    this.chartDataBmi.datasets[0].data = bmiArr;

    labelsArr.reverse();
    this.chartDataWeight.labels = labelsArr;
    this.chartDataCalories.labels = labelsArr;
    this.chartDataBmi.labels = labelsArr;
  },
};
</script>
