#include <iostream>
using namespace std;

int main() {
    int N = 8;
    char start[8];
    char end[8];
    char res[8];
    
    for (int i = 0; i < N; i++) {
        cin >> start[i];
    }

    for (int i = 0; i < N; i++) {
        cin >> end[i];
    }

    int startHour = (start[0] - '0') * 10 + (start[1] - '0');
    int startMinute = (start[3] - '0') * 10 + (start[4] - '0');
    int startSecond = (start[6] - '0') * 10 + (start[7] - '0');

    int startSum = startHour * 60 * 60 + startMinute * 60 + startSecond;

    int endHour = (end[0] - '0') * 10 + (end[1] - '0');
    int endMinute = (end[3] - '0') * 10 + (end[4] - '0');
    int endSecond = (end[6] - '0') * 10 + (end[7] - '0');

    int endSum = endHour * 60 * 60 + endMinute * 60 + endSecond;

    if (endSum < startSum) {
        endSum += 24 * 60 * 60;
    }

    int resSum = (endSum - startSum);

    int resHour = resSum / 60 / 60;
    int resMinute = (resSum - resHour * 60 * 60) / 60;
    int resSecond = (resSum - resHour * 60 * 60 - resMinute * 60);
    

    

    if (resHour < 10) {
        res[0] = '0';
        res[1] = resHour + '0';
    } else{
        res[0] = resHour / 10 + '0';
        res[1] = resHour % 10 + '0';
    }

    res[2] = ':';


    if (resMinute < 10) {
        res[3] = '0';
        res[4] = resMinute + '0';
    }
    else {
        res[3] = resMinute / 10 + '0';
        res[4] = resMinute % 10 + '0';
    }

    res[5] = ':';

    if (resSecond < 10) {
        res[6] = '0';
        res[7] = resSecond + '0';
    }
    else {
        res[6] = resSecond / 10 + '0';
        res[7] = resSecond % 10 + '0';
    }


    for (int i = 0; i < N; i++) {
        cout << res[i];
    }
}