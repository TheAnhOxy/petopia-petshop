// import React from "react";
// import ReactApexChart from "react-apexcharts";

// function ChartSection({ darkMode }) {
//   const series = [
//     {
//       name: "Revenue",
//       data: [6300, 6200, 6150, 6400, 6300, 6250, 6100],
//     },
//     {
//       name: "Revenue (previous period)",
//       data: [6500, 6700, 6400, 6200, 6600, 6700, 6550],
//     },
//   ];

//   const options = {
//     chart: {
//       type: "area",
//       height: 350,
//       toolbar: { show: false },
//       zoom: { enabled: false },
//       background: "transparent",
//     },
//     dataLabels: { enabled: false },
//     stroke: { curve: "smooth", width: 3 },
//     markers: {
//       size: 5,
//       colors: ["white"],
//       strokeColors: ["#1E90FF", "#FFA500"],
//       strokeWidth: 2,
//       hover: { size: 7 },
//     },
//     tooltip: {
//       shared: true,
//       intersect: false,
//       theme: darkMode ? "dark" : "light",
//       y: { formatter: (val) => `$${val}` },
//     },
//     fill: {
//       type: "gradient",
//       gradient: {
//         shadeIntensity: 1,
//         inverseColors: false,
//         opacityFrom: 0.4,
//         opacityTo: 0.1,
//         stops: [0, 90, 100],
//         colorStops: [],
//       },
//     },
//     grid: {
//       borderColor: darkMode ? "#334155" : "#e2e8f0",
//       strokeDashArray: 4,
//       row: {
//         colors: [darkMode ? "#1e293b" : "#f8fafc"],
//         opacity: 0.2,
//       },
//     },
//     colors: ["#1E90FF", "#FFA500"],
//     xaxis: {
//       categories: [
//         "01 Feb",
//         "02 Feb",
//         "03 Feb",
//         "04 Feb",
//         "05 Feb",
//         "06 Feb",
//         "07 Feb",
//       ],
//       labels: {
//         style: {
//           colors: darkMode
//             ? Array(7).fill("#CBD5E1")
//             : Array(7).fill("#475569"),
//         },
//       },
//       axisBorder: { show: false },
//       axisTicks: { color: darkMode ? "#334155" : "#cbd5e1" },
//     },
//     yaxis: {
//       labels: {
//         formatter: (value) => `$${value}`,
//         style: { colors: darkMode ? "#CBD5E1" : "#475569" },
//       },
//     },
//     legend: {
//       position: "bottom",
//       labels: {
//         colors: darkMode ? "#E2E8F0" : "#334155",
//         useSeriesColors: false,
//       },
//       fontSize: "14px",
//       fontWeight: 500,
//     },
//     theme: {
//       mode: darkMode ? "dark" : "light",
//     },
//   };

//   return (
//     <div
//       className={`w-full p-6 ${
//         darkMode ? "bg-gray-800" : "bg-white"
//       } rounded-2xl shadow-md transition-colors`}
//     >
//       <div className="flex justify-between items-center mb-2">
//         <h2
//           className={`text-3xl font-bold ${
//             darkMode ? "text-white" : "text-gray-800"
//           }`}
//         >
//           $45,385
//         </h2>
//         <span className="text-green-500 font-semibold text-sm">12.5% ↑</span>
//       </div>
//       <p
//         className={`text-sm mb-4 ${
//           darkMode ? "text-gray-400" : "text-gray-500"
//         }`}
//       >
//         Sales this week
//       </p>
//       <ReactApexChart
//         options={options}
//         series={series}
//         type="area"
//         height={350}
//       />
//       <div
//         className={`mt-4 text-center text-sm ${
//           darkMode ? "text-gray-400" : "text-gray-500"
//         }`}
//       >
//         Last 7 days
//       </div>
//     </div>
//   );
// }

// export default ChartSection;
