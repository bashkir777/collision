export function validateSpeed(value){
    if (isNaN(value)){
        return false;
    }
    return Number(value) >= 1 && Number(value) <= 10;

}
export function validateWeight(value){
    if (isNaN(value)){
        return false;
    }
    return Number(value) <= 50 && Number(value) >= 1;
}