import { Injectable} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Templates } from './template.entity';

@Injectable()
export class TemplateService {
    constructor(
        @InjectRepository(Templates)
        private readonly TemplateRepository: Repository<Templates>,
        ) {}

  async create(Template: Templates): Promise<Templates> {
    console.log("IN CREATE" + Template)
    return await this.TemplateRepository.save(Template);
  }

  async findAll(): Promise<Templates[]> {
    return await this.TemplateRepository.find();
  }

  async findOne(int: number): Promise<Templates> {
    try{
      return await this.TemplateRepository.findOne({id: int});
    }catch(err){
      throw  err;
    }
  }

  async deleteOne(int: number): Promise<Templates> {
    try {
        const toDelete = await this.TemplateRepository.findOne({id: int});
        await this.TemplateRepository.delete({id: int});
        return toDelete;
    } catch (e) {
        console.log(e);
    }
  }

  async update(int: number,Template: Templates): Promise<Templates> {
      console.log("IN UPDATE")
      try {
          await this.TemplateRepository.update(int,Template)
          console.log(Template)
          return Template;
      } catch (e) {
          console.log(e)
      }
}
}
