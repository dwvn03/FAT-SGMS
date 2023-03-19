const yearSelect = document.querySelector('select[name="year"]');
const fromDateSelect = document.querySelector('select[name="fromDate"]');
// reload week for the year chosen
let datesCache = {};
yearSelect.addEventListener('change', async () => {
    let dates;
    if (datesCache[yearSelect.value]) {
        dates = datesCache[yearSelect.value];
    } else {
        let searchParam = new URLSearchParams();
        searchParam.append("year", yearSelect.value);

        let response = await fetch("/api/date", {
            method: "POST",
            body: searchParam
        })

        let data = await response.text();
        const dateArray = data.split(/[\[,\]]/).filter(date => date !== "" && date !== " ");
        const mondays = dateArray.slice(0, dateArray.length / 2);
        const sundays = dateArray.slice(dateArray.length / 2);
        dates = {mondays, sundays};
        datesCache[yearSelect.value] = dates;
    }

    fromDateSelect.innerHTML = "";
    for (let i = 0; i < dates.mondays.length; i++) {
        const option = document.createElement('option');
        option.value = dates.mondays[i];
        option.innerText = `${dates.mondays[i]} to ${dates.sundays[i]}`;
        fromDateSelect.appendChild(option);
    }
})

fromDateSelect.addEventListener('change', () => {
    document.querySelector('form').submit();
});
