
const DateConvert = (date) => {
	return new Date(date).toLocaleString('ru', {
			year: 'numeric',
			month: 'long',
			day: 'numeric'
	});
};

export default DateConvert;