const CANDIDATE_STATUS = {
    'NOT_REVIEWED': 'Не рассмотрено',
    'REVIEWED': 'Рассмотрено',
    'SATISFYING': 'Подходит',
    'NOT_SATISFYING': 'Не подходит',
    'TEST_SENT': 'Отправлен тест',
    'INVITED_TO_INTERVIEW': 'Приглашен на интервью',
    'INTERVIEWS_CONDUCTED': 'Интервью проведено',
    'CURRENT_EMPLOYEE': 'Штат',
    'IN_RESERVE': 'Резерв',
    'INTERN': 'Стажёр',
    'FAILED_INTERVIEW': 'Не прошел интервью',
};

const getStatus = (status) => {
    return CANDIDATE_STATUS[status]
};

export default getStatus;

