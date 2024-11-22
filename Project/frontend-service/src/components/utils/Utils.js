class Utils {
    checkFeature() {
        const feature  =  localStorage.getItem(("feature"))
        return feature != null ? feature : "CLIENT"
    }
}

export default Utils