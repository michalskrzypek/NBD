printjson(
    db.people.aggregate(
        [
            {
                $project: {
                    nationality: "$nationality",
                    height: { $toDecimal: "$height" },
                    weight: { $toDecimal: "$weight" },
                }
            },
            {
                $set: {
                    heightInM: {
                        $divide: ["$height", 100]
                    }
                }
            },
            {
                $set: {
                    heightInM2: {
                        $pow: ["$heightInM", 2]
                    }
                }
            },
            {
                $set: {
                    BMI: {
                        $divide: ["$weight", "$heightInM2"]
                    }
                }
            },
            {
                $group: {
                    _id: "$nationality",
                    avgBMI: {
                        $avg: "$BMI"
                    },
                    minBMI: {
                        $min: "$BMI"
                    },
                    maxBMI: {
                        $max: "$BMI"
                    },
                }
            }
        ]
    ).toArray()
)