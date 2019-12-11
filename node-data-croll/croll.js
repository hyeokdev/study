const axios = require("axios");
const cheerio = require("cheerio");
const log = console.log;

//mysql db 연결
const mysql = require('mysql');
const connection = mysql.createConnection({
    host: 'localhost',
    port: '3306',
    user: 'root',
    password: '1q2w3e4r!',
    database: 'croll'
});

connection.connect();

//스크래핑 
const getHtml = async() => {
    try {
        return await axios.get("https://www.yna.co.kr/sports/all");
    } catch(error) {
        log(error);
    }
};

const insertValues = function(values) {
    connection.query('INSERT INTO sports_news (title, url, image_url, image_alt, summary, date) VALUES ?', [values],
    function(error,result) {
        log("result: " + result)
    });
}

//파싱
getHtml()
    .then(html => {
        let ulList = [];
        const $ = cheerio.load(html.data);
        const $bodyList = $("div.headlines ul").children("li.section02");

        $bodyList.each(function(i, el) {
            ulList[i] = {
                title: $(this).find('strong.news-tl a').text(),
                url: $(this).find('strong.news-tl a').attr('href'),
                image_url: $(this).find('p.poto a img').attr('src'),
                image_alt: $(this).find('p.poto a img').attr('alt'),
                summary: $(this).find('p.lead').text().slice(0, -11),
                date: $(this).find('span.p-time').text()
            };
        });
        const data = ulList.filter(n => n.title);
        const values = Object.keys(data).map(function(index) {
            const obj = data[index];
            return Object.keys(obj).map(function(val) {
                return obj[val];
            })
        });
        insertValues(values);
    })
    .then(res => log("success"));