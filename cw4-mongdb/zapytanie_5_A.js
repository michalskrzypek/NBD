printjson(
    db.people.aggregate(
        [{
            $match: {
                sex: "Female",
                nationality: "Poland",
            }
        },
        {
            $unwind: "$credit"
        },
        {
            $project: {
                balance: {$toDecimal: "$credit.balance"},
                currency: "$credit.currency"
            }
        },
        {
            $group: {
                _id: "$currency",
                avgBalance: {
                    $avg: "$balance"
                },
                totalBalance: {
                    $sum: "$balance"
                },
            }
        }
        ]
    ).toArray()
)