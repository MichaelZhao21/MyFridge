const vision = require('@google-cloud/vision');
const express = require('express');
const app = express();

app.get('/', (req, res) => {
    res
        .status(403)
        .send('FORBIDDEN AREA')
        .end();
});

app.get('/scan', (req, res) => {
    res.status(200).end();
    detect(req.get("picture"));
});

app.get('/food', (req, res) => {
    res.status(200).end();
    console.log(getFood(req.get("names")));
});

const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
    console.log(`App listening on port ${PORT}`);
});

async function detect(picture) { // TODO: FIX
    const client = new vision.ImageAnnotatorClient();

    const [result] = await client.labelDetection(picture);
    const labels = result.labelAnnotations;
    console.log('Labels:');
    labels.forEach(label => console.log(label.description));
}

function getFood(foods) {
    const https = require('https')
    const options = {
        hostname: 'api.edamam.com',
        port: 443,
        path: `/search?q=${foods}&app_id=0fa9de55&app_key=c61f70c58040fabee8cee8452cfd2cac`,
        method: 'GET'
    }

    const req = https.request(options, res => {
        console.log(`statusCode: ${res.statusCode}`)

        res.on('data', d => {
            process.stdout.write(d)
        })

    })

    req.on('error', error => {
        console.error(error)
    })

    req.end()

}

module.exports = app;