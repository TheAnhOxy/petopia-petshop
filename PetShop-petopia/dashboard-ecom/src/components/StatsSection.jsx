// "use client";

// import { useEffect, useRef, useState } from "react";

// function StatsSection({ darkMode }) {
//   const [isVisible, setIsVisible] = useState(false);
//   const sectionRef = useRef(null);

//   useEffect(() => {
//     const observer = new IntersectionObserver(
//       ([entry]) => {
//         if (entry.isIntersecting) {
//           setIsVisible(true);
//         }
//       },
//       { threshold: 0.1 }
//     );

//     if (sectionRef.current) {
//       observer.observe(sectionRef.current);
//     }

//     return () => {
//       if (sectionRef.current) {
//         observer.unobserve(sectionRef.current);
//       }
//     };
//   }, []);

//   const metrics = [
//     {
//       title: "Total Revenue",
//       value: "$125,430",
//       change: "+12.5%",
//       trend: "up",
//       icon: (
//         <svg
//           className="w-6 h-6"
//           fill="none"
//           stroke="currentColor"
//           viewBox="0 0 24 24"
//         >
//           <path
//             strokeLinecap="round"
//             strokeLinejoin="round"
//             strokeWidth={2}
//             d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
//           />
//         </svg>
//       ),
//       color: "from-[#7b4f35] to-[#6a4330]",
//     },
//     {
//       title: "Active Users",
//       value: "8,542",
//       change: "+8.2%",
//       trend: "up",
//       icon: (
//         <svg
//           className="w-6 h-6"
//           fill="none"
//           stroke="currentColor"
//           viewBox="0 0 24 24"
//         >
//           <path
//             strokeLinecap="round"
//             strokeLinejoin="round"
//             strokeWidth={2}
//             d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"
//           />
//         </svg>
//       ),
//       color: "from-blue-500 to-blue-600",
//     },
//     {
//       title: "Conversion Rate",
//       value: "3.24%",
//       change: "+2.1%",
//       trend: "up",
//       icon: (
//         <svg
//           className="w-6 h-6"
//           fill="none"
//           stroke="currentColor"
//           viewBox="0 0 24 24"
//         >
//           <path
//             strokeLinecap="round"
//             strokeLinejoin="round"
//             strokeWidth={2}
//             d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"
//           />
//         </svg>
//       ),
//       color: "from-green-500 to-green-600",
//     },
//     {
//       title: "Avg. Order Value",
//       value: "$156.50",
//       change: "+5.7%",
//       trend: "up",
//       icon: (
//         <svg
//           className="w-6 h-6"
//           fill="none"
//           stroke="currentColor"
//           viewBox="0 0 24 24"
//         >
//           <path
//             strokeLinecap="round"
//             strokeLinejoin="round"
//             strokeWidth={2}
//             d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z"
//           />
//         </svg>
//       ),
//       color: "from-purple-500 to-purple-600",
//     },
//   ];

//   return (
//     <div
//       ref={sectionRef}
//       className={`transition-all duration-1000 delay-300 transform ${
//         isVisible ? "opacity-100 translate-y-0" : "opacity-0 translate-y-10"
//       }`}
//     >
//       <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
//         {metrics.map((metric, index) => (
//           <div
//             key={index}
//             className={`rounded-xl shadow-lg p-6 transition-all duration-300 hover:shadow-2xl hover:scale-105 ${
//               darkMode ? "bg-gray-800" : "bg-white"
//             }`}
//             style={{ transitionDelay: `${index * 100}ms` }}
//           >
//             {/* Icon with gradient background */}
//             <div
//               className={`w-14 h-14 rounded-xl bg-gradient-to-br ${metric.color} flex items-center justify-center text-white mb-4 shadow-lg`}
//             >
//               {metric.icon}
//             </div>

//             {/* Title */}
//             <h3
//               className={`text-sm font-medium mb-2 ${
//                 darkMode ? "text-gray-400" : "text-gray-600"
//               }`}
//             >
//               {metric.title}
//             </h3>

//             {/* Value and Change */}
//             <div className="flex items-end justify-between">
//               <p className="text-2xl font-bold text-[#7b4f35]">
//                 {metric.value}
//               </p>
//               <div
//                 className={`flex items-center gap-1 ${
//                   metric.trend === "up" ? "text-green-500" : "text-red-500"
//                 }`}
//               >
//                 {metric.trend === "up" ? (
//                   <svg
//                     className="w-4 h-4"
//                     fill="currentColor"
//                     viewBox="0 0 20 20"
//                   >
//                     <path
//                       fillRule="evenodd"
//                       d="M12 7a1 1 0 110-2h5a1 1 0 011 1v5a1 1 0 11-2 0V8.414l-4.293 4.293a1 1 0 01-1.414 0L8 10.414l-4.293 4.293a1 1 0 01-1.414-1.414l5-5a1 1 0 011.414 0L11 10.586 14.586 7H12z"
//                       clipRule="evenodd"
//                     />
//                   </svg>
//                 ) : (
//                   <svg
//                     className="w-4 h-4"
//                     fill="currentColor"
//                     viewBox="0 0 20 20"
//                   >
//                     <path
//                       fillRule="evenodd"
//                       d="M12 13a1 1 0 100 2h5a1 1 0 001-1V9a1 1 0 10-2 0v2.586l-4.293-4.293a1 1 0 00-1.414 0L8 9.586 3.707 5.293a1 1 0 00-1.414 1.414l5 5a1 1 0 001.414 0L11 9.414 14.586 13H12z"
//                       clipRule="evenodd"
//                     />
//                   </svg>
//                 )}
//                 <span className="text-sm font-semibold">{metric.change}</span>
//               </div>
//             </div>

//             {/* Progress bar */}
//             <div
//               className={`mt-4 h-2 rounded-full overflow-hidden ${
//                 darkMode ? "bg-gray-700" : "bg-gray-200"
//               }`}
//             >
//               <div
//                 className={`h-full bg-gradient-to-r ${metric.color} transition-all duration-1000`}
//                 style={{ width: isVisible ? "75%" : "0%" }}
//               />
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// }

// export default StatsSection;
