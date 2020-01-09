$(document).ready(function () {


    var options1 = {
        tooltips: {
            enabled: true
        },
        legend: {
            display: true,
            position: 'top',
            align: 'start'
        },
        // plugins: {
        //     datalabels: {
        //         formatter: (value, ctx) => {
        //             let sum = 0;
        //             let dataArr = ctx.chart.data.datasets[0].data;
        //             dataArr.map(data => {
        //                 sum += data;
        //             });
        //             let percentage = (value*100 / sum).toFixed(2)+"%";
        //             return "50" ;
        //         },
        //         color: '#fff',
        //     }
        // }
    };


    var ctx = document.getElementById("pieChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ["Total", "Rent More Than One Property"],
            datasets: [{
                backgroundColor: [
                    "#34495e",
                    "#e74c3c",
                    "#95a5a6",




                ],
                data: [59.67, 40.43]
            }]
        },
        options: options1,

    });


    var jsonfileavrageTotal = {
        "jsonarray": [
            {
                "Region": "Central Region",
                "Avarage": 114.38
            },
            {
                "Region": "East Region",
                "Avarage": 117.23
            },
            {
                "Region": "North Region",
                "Avarage": 81.99
            },
            {
                "Region": "North-East Region",
                "Avarage": 79.88
            },
            {
                "Region": "West Region",
                "Avarage": 117.83
            }
        ]
    };



    var labels = jsonfileavrageTotal.jsonarray.map(function (e) {
        return e.Region;
    });
    var data = jsonfileavrageTotal.jsonarray.map(function (e) {
        return e.Avarage;
    });;

    var ctx = $("#hive_1Chart");
    var config = {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Regions',
                data: data,
                backgroundColor: [
                    "#f1c40f",
                    "#9b59b6",
                    "#2ecc71",
                    "#3498db",
                    "#95a5a6",
                    "#e74c3c",
                    "#34495e"
                ],
            }]
        },
        options: {
            title: {
                display: false,
                text: 'Average price of Private room rental by neighbourhood group'
            }, legend: {
                display: false
            },
        }
    };

    var chart = new Chart(ctx, config);

    //////////////////


    var NabourhoodNoOfRental = {
        "jsonObject": [
            {
                "NeighbourhoodGroup": "Central Region",
                "Rentals": 6276
            },
            {
                "NeighbourhoodGroup": "East Region",
                "Rentals": 507
            },
            {
                "NeighbourhoodGroup": "North Region",
                "Rentals": 203
            },
            {
                "NeighbourhoodGroup": "North-East Region",
                "Rentals": 343
            },
            {
                "NeighbourhoodGroup": "West Region",
                "Rentals": 540
            }
        ]
    };

    var labelstoNoOfrentalsNeg = NabourhoodNoOfRental.jsonObject.map(function (e) {
        return e.NeighbourhoodGroup;
    });
    var datatoNoofRentalNeg = NabourhoodNoOfRental.jsonObject.map(function (e) {
        return e.Rentals;
    });

    var ctx = $("#mreduce2_Chart");
    var config = {
        type: 'bar',
        data: {
            labels: labelstoNoOfrentalsNeg,
            datasets: [{
                label: 'Regions',
                data: datatoNoofRentalNeg,
                backgroundColor: [
                    "#f1c40f",
                    "#9b59b6",
                    "#2ecc71",
                    "#3498db",
                    "#95a5a6",
                    "#e74c3c",
                    "#34495e"
                ],
            }]
        },
        options: {
            title: {
                display: false,
                text: 'Average price of Private room rental by neighbourhood group'
            },
            legend: {
                display: false
            },
        }

    };

    var chart2 = new Chart(ctx, config);

    //////////////////////Quection 7////////////////////////////////////

    var reviewsMonths = {
        "jsonObject": [
            { "Last_Review_Month": "Jan", "NumberOfReviews": 2105 },
            { "Last_Review_Month": "Feb", "NumberOfReviews": 1921 },
            { "Last_Review_Month": "Mar", "NumberOfReviews": 1989 },
            { "Last_Review_Month": "Apr", "NumberOfReviews": 2131 },
            { "Last_Review_Month": "May", "NumberOfReviews": 3726 },
            { "Last_Review_Month": "Jun", "NumberOfReviews": 6436 },
            { "Last_Review_Month": "Juy", "NumberOfReviews": 17862 },
            { "Last_Review_Month": "Aug", "NumberOfReviews": 60928 },
            { "Last_Review_Month": "Sep", "NumberOfReviews": 976 },
            { "Last_Review_Month": "Oct", "NumberOfReviews": 749 },
            { "Last_Review_Month": "Nov", "NumberOfReviews": 905 },
            { "Last_Review_Month": "Dec", "NumberOfReviews": 1540 }
        ]
    };

    var labelstoNoOfrentalsNeg = reviewsMonths.jsonObject.map(function (e) {
        return e.Last_Review_Month;
    });
    var datatoNoofRentalNeg = reviewsMonths.jsonObject.map(function (e) {
        return e.NumberOfReviews;
    });

    var ctx = $("#2sparkChart");
    var config = {
        type: 'bar',
        data: {
            labels: labelstoNoOfrentalsNeg,
            datasets: [{
                label: 'Regions',
                data: datatoNoofRentalNeg,
                backgroundColor: [
                    '#e6194b', 
                    '#3cb44b', 
                    '#ffe119', 
                    '#4363d8',
                    '#f58231', 
                    '#911eb4', 
                    '#46f0f0', 
                    '#f032e6', 
                    '#bcf60c', 
                    '#fabebe', 
                    '#008080', 
                    '#e6beff', 
                    '#9a6324',
                  
                ],
            }]
        },
        options: {
            title: {
                display: false,
            
            },
            legend: {
                display: false
            },
        }

    };

    var chart2 = new Chart(ctx, config);

});