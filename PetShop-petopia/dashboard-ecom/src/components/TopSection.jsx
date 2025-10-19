// import React, { useState } from "react";

// function TopSection({ darkMode }) {
//   const [activeTab, setActiveTab] = useState("products");

//   const topProducts = [
//     {
//       name: "iPhone 14 Pro",
//       price: "$445,467",
//       change: "+2.5%",
//       img: "https://cdn-icons-png.flaticon.com/512/5968/5968947.png",
//     },
//     {
//       name: 'Apple iMac 27"',
//       price: "$256,982",
//       change: "+12.5%",
//       img: "https://cdn-icons-png.flaticon.com/512/270/270798.png",
//     },
//     {
//       name: "Apple Watch SE",
//       price: "$201,869",
//       change: "-1.35%",
//       img: "https://cdn-icons-png.flaticon.com/512/5977/5977595.png",
//     },
//     {
//       name: "Apple iPad Air",
//       price: "$103,967",
//       change: "+12.5%",
//       img: "https://cdn-icons-png.flaticon.com/512/888/888879.png",
//     },
//     {
//       name: 'Apple iMac 24"',
//       price: "$98,543",
//       change: "-2%",
//       img: "https://cdn-icons-png.flaticon.com/512/270/270798.png",
//     },
//   ];

//   const topCustomers = [
//     {
//       name: "Customer A",
//       sales: "$300,000",
//       change: "+5.0%",
//       img: "https://cdn-icons-png.flaticon.com/512/3135/3135715.png",
//     },
//     {
//       name: "Customer B",
//       sales: "$250,000",
//       change: "+3.5%",
//       img: "https://cdn-icons-png.flaticon.com/512/4140/4140048.png",
//     },
//     {
//       name: "Customer C",
//       sales: "$200,000",
//       change: "-0.5%",
//       img: "https://cdn-icons-png.flaticon.com/512/4140/4140037.png",
//     },
//     {
//       name: "Customer D",
//       sales: "$150,000",
//       change: "+7.0%",
//       img: "https://cdn-icons-png.flaticon.com/512/4140/4140051.png",
//     },
//     {
//       name: "Customer E",
//       sales: "$120,000",
//       change: "-1.0%",
//       img: "https://cdn-icons-png.flaticon.com/512/4140/4140056.png",
//     },
//   ];

//   return (
//     <div
//       className={`w-full p-6 rounded-lg shadow-lg h-full overflow-hidden ${
//         darkMode ? "bg-gray-800 text-white" : "bg-white text-gray-800"
//       }`}
//     >
//       <div className="flex space-x-6 border-b mb-4 pb-2">
//         <button
//           onClick={() => setActiveTab("products")}
//           className={`pb-1 font-semibold transition-colors ${
//             activeTab === "products"
//               ? "border-b-2 border-blue-500 text-blue-500 dark:text-blue-400"
//               : "text-gray-500 dark:text-gray-400"
//           }`}
//         >
//           Top Products
//         </button>
//         <button
//           onClick={() => setActiveTab("customers")}
//           className={`pb-1 font-semibold transition-colors ${
//             activeTab === "customers"
//               ? "border-b-2 border-blue-500 text-blue-500 dark:text-blue-400"
//               : "text-gray-500 dark:text-gray-400"
//           }`}
//         >
//           Top Customers
//         </button>
//       </div>

//       <div className="overflow-y-auto max-h-[400px] pr-2">
//         {activeTab === "products" ? (
//           <ul className="space-y-4">
//             {topProducts.map((product, index) => (
//               <li key={index} className="flex items-center justify-between">
//                 <div className="flex items-center space-x-4">
//                   <img
//                     src={product.img}
//                     alt={product.name}
//                     className="w-10 h-10 rounded"
//                   />
//                   <div>
//                     <p className="font-medium">{product.name}</p>
//                     <p
//                       className={`text-sm ${
//                         product.change.startsWith("-")
//                           ? "text-red-500"
//                           : "text-green-500"
//                       }`}
//                     >
//                       {product.change}{" "}
//                       <span className="text-gray-500 dark:text-gray-400">
//                         vs last month
//                       </span>
//                     </p>
//                   </div>
//                 </div>
//                 <span className="font-semibold">{product.price}</span>
//               </li>
//             ))}
//           </ul>
//         ) : (
//           <ul className="space-y-4">
//             {topCustomers.map((customer, index) => (
//               <li key={index} className="flex items-center justify-between">
//                 <div className="flex items-center space-x-4">
//                   <img
//                     src={customer.img}
//                     alt={customer.name}
//                     className="w-10 h-10 rounded-full"
//                   />
//                   <p className="font-medium">{customer.name}</p>
//                 </div>
//                 <div className="text-right">
//                   <p
//                     className={`font-semibold ${
//                       customer.change.startsWith("-")
//                         ? "text-red-500"
//                         : "text-green-500"
//                     }`}
//                   >
//                     {customer.sales}
//                   </p>
//                   <p className="text-sm text-gray-500 dark:text-gray-400">
//                     {customer.change} vs last month
//                   </p>
//                 </div>
//               </li>
//             ))}
//           </ul>
//         )}
//       </div>
//     </div>
//   );
// }

// export default TopSection;
