// import dark from "../../image/Dark_Background_image-01.png"
// import light from "../../image/CacheMoney_MoneyBackground_Smaller.png"
// import logo from "../../image/CacheMoney-logo.png"
// import logoDark from "../../image/CacheMoney-logo-dark.png"
import config from "../../config"

export const lightTheme = {
    gentle: "#6c896a",
    background: "#fff",
    text: "#fff",
    shadow: "#3e4f3d",
    bread: "#3d703a",
    highlight: "#d3a940",
    backlight: "#d3a940",
    ultrahighlight: "#f6ba65",
    greyness: "#4c4c4b",
    specialButtons: "#d3a940",
    error: "#cc3040",
    splashBackground: `url(${config.url_img}static/media/Background_image-01.7bea5d27b65a2d0c5b9a.png)`,
    moneyBackground: `url(${config.url_img}static/media/CacheMoney_MoneyBackground_Smaller.94ddae7aa0898659895e.png)`,
    cacheMoney: `url(${config.url_img}static/media/CacheMoney-logo.e3731fba85899852b417.png)`,
}

export const darkTheme = {
    gentle: "#4c4c4b",
    background: "#4c4c4b",
    text: "#6c896a",
    shadow: "#101010",
    bread: "#3e4f3d",
    highlight: "#3d703a",
    backlight: "#101010",
    ultrahighlight: "#6c896a",
    specialButtons: "#3e4f3d",
    greyness: "#4c4c4b",
    error: "#cc3040",
    splashBackground: `url(${config.url_img}static/media/Dark_Background_image-01.fa8d53963c1bb0c6ce45.png)`,
    moneyBackground: `url(${config.url_img}static/media/CacheMoney_MoneyBackground_Dark.904d1499ea29d61935c7.png)`,
    cacheMoney: `url(${config.url_img}static/media/CacheMoney-logo-dark.533bbb747a4eca262de6.png)`,
}