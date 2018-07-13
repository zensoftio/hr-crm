
const InitWorkHours = (value) => {
    if(value === 'FULL_TIME'){
        return 'Полный рабочий день'
    } else if(value === 'PART_TIME'){
        return 'Гибкий график'
    } else if(value === 'REMOVE_JOB'){
        return 'Удаленная работа'
    } else return ''
}

export default InitWorkHours;