printjson(db.people.aggregate(
    [
        {
        $project: {
            sex: "$sex",
            convertedHeight: { $toDecimal: "$height" },
            convertedWeight: { $toDecimal: "$weight" },
                }
        },
        {
            $group: {
                _id: "$sex",
                avgHeight: {$avg: "$convertedHeight"},
                avgWeight: { $avg: "$convertedWeight" },
            }
        }
    ]
).toArray())