var mapFunction1 = function () {
    if (this.sex === "Female" && this.nationality === "Poland") {
        const credits = this.credit;
        for (let index = 0; index < credits.length; index++) {
            const credit = credits[index];
            emit(credit.currency, {
                balance: parseFloat(credit.balance),
                count: 1
            });
        }
    }
};

var reduceFunction1 = function (keyCurrency, valuesCredit) {
    var balanceSum = 0;
    var totalCount = 0;

    for (let i = 0; i < valuesCredit.length; i++) {
        const element = valuesCredit[i];
        balanceSum += element.balance;
        totalCount += element.count;
    }

    return {
        balance: balanceSum,
        count: totalCount
    }
};

var finalizeFunction1 = function (keyCurrency, reducedVal) {
    return {
        totalBalance: reducedVal.balance,
        balanceAverage: reducedVal.balance / reducedVal.count
    }
};

printjson(db.people.mapReduce(
    mapFunction1,
    reduceFunction1,
    {
        finalize: finalizeFunction1,
        out: { inline: 1 }
    }
))