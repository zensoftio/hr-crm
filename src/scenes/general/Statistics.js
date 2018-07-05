import React from 'react';
import Header from './Header';

const Statistics = () => {

	const statistics = [
		{
			desc: "Общее количество позиций",
			amount: 123
		},
		{
			desc: "Открытые Позиции",
			amount: 234
		},
		{
			desc: "Закрытые Позиции",
			amount: 456
		}
	];
  return (
    <div>
      <Header title="Статистика"/>
			<div className="statistic">
				<ul>
					{statistics.map((stat, i) => {
						return (
							<li key={i}>
								<span>{stat.desc} </span>
								<span>{stat.amount}</span>
							</li>
						)
					})}
				</ul>
			</div>
    </div>
  )
}

export default Statistics;