package com.erkprog.zensofthrcrm.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.erkprog.zensofthrcrm.data.entity.Candidate;
import com.erkprog.zensofthrcrm.data.entity.Comment;
import com.erkprog.zensofthrcrm.data.entity.Criteria;
import com.erkprog.zensofthrcrm.data.entity.Cv;
import com.erkprog.zensofthrcrm.data.entity.Department;
import com.erkprog.zensofthrcrm.data.entity.Evaluation;
import com.erkprog.zensofthrcrm.data.entity.Interview;
import com.erkprog.zensofthrcrm.data.entity.Interviewer;
import com.erkprog.zensofthrcrm.data.entity.Position;
import com.erkprog.zensofthrcrm.data.entity.Request;
import com.erkprog.zensofthrcrm.data.entity.Requirement;
import com.erkprog.zensofthrcrm.data.entity.Template;
import com.erkprog.zensofthrcrm.data.entity.User;
import com.erkprog.zensofthrcrm.data.entity.Vacancy;
import com.erkprog.zensofthrcrm.utils.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

  private static final String DB_NAME = "HR_CRM";
  private static final int DB_VERSION = 6;

  // Tables
  private static final String INTERVIEWS = "INTERVIEWS";
  private static final String CRITERIAS = "CRITERIAS";
  private static final String VACANCIES = "VACANCIES";
  private static final String REQUESTS = "REQUESTS";
  private static final String REQUIREMENTS = "REQUIREMENTS";
  private static final String DEPARTMENTS = "DEPARTMENTS";
  private static final String POSITIONS = "POSITIONS";
  private static final String INTERVIEWERS = "INTERVIEWERS";
  private static final String CANDIDATES = "CANDIDATES";
  private static final String EVALUATIONS = "EVALUATIONS";
  private static final String CVS = "CVS";
  private static final String USERS = "USERS";
  private static final String COMMENTS = "COMMENTS";
  private static final String TEMPLATES = "TEMPLATES";

  // Columns and stuff
  private static final String SKYPE = "SKYPE";
  private static final String ID = "_id";
  private static final String POSITION = "DATE";
  private static final String NAME = "NAME";
  private static final String URL = "URL";
  private static final String FIRST_NAME = "FIRST_NAME";
  private static final String LAST_NAME = "LAST_NAME";
  private static final String STATUS = "STATUS";
  private static final String COMMENT = "COMMENT";
  private static final String RATE = "RATE";
  private static final String EXPERIENCE = "EXPERIENCE";
  private static final String LEVEL = "LEVEL";
  private static final String PHONE = "PHONE";
  private static final String CREATED = "CREATED";
  private static final String EMAIL = "EMAIL";
  private static final String LAST_PUBLISHED = "LAST_PUBLISHED";
  private static final String SUBJECT = "SUBJECT";
  private static final String TYPE = "TYPE";
  private static final String CONTENT = "CONTENT";
  private static final String COUNT = "COUNT";
  private static final String CANDIDATE = "CANDIDATE";
  private static final String DATE = "DATE";
  private static final String USER = "USER";
  private static final String CREATED_BY = "CREATED_BY";
  private static final String TEXT = "TEXT";
  private static final String DEPARTMENT = "DEPARTMENT";
  private static final String CRITERIA = "CRITERIA";
  private static final String MODIFIED = "MODIFIED";

  // Create tables strings

  private static final String CREATE_TABLE_REQUESTS = "CREATE TABLE IF NOT EXISTS " +
      REQUESTS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      POSITION + ID + " TEXT, " +
      REQUIREMENTS + ID + " TEXT, " +
      CREATED_BY + ID + " TEXT, " +
      COUNT + " INTEGER, " +
      MODIFIED + " TEXT, " +
      STATUS + " TEXT, " +
      CREATED + " TEXT);";

  private static final String CREATE_TABLE_POSITIONS = "CREATE TABLE IF NOT EXISTS " +
      POSITIONS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      DEPARTMENT + ID + " TEXT, " +
      NAME + " TEXT);";


  private static final String CREATE_TABLE_DEPARTMENTS = "CREATE TABLE IF NOT EXISTS " +
      DEPARTMENTS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      NAME + " TEXT);";

  private static final String CREATE_TABLE_REQUIREMENTS = "CREATE TABLE IF NOT EXISTS " +
      REQUIREMENTS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      DEPARTMENT + ID + " INTEGER, " +
      TYPE + " TEXT, " +
      NAME + " TEXT);";

  private static final String CREATE_TABLE_CVC = "CREATE TABLE IF NOT EXISTS " +
      CVS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      URL + " TEXT, " +
      CREATED + " TEXT);";


  private static final String CREATE_TABLE_INTERVIEWS = "CREATE TABLE IF NOT EXISTS " +
      INTERVIEWS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      DATE + " TEXT, " +
      INTERVIEWERS + ID + " TEXT, " +
      CANDIDATE + ID + " TEXT, " +
      STATUS + " TEXT);";


  private static final String CREATE_TABLE_INTERVIEWERS = "CREATE TABLE IF NOT EXISTS " +
      INTERVIEWERS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      USER + ID + " TEXT, " +
      COMMENT + " TEXT, " +
      EVALUATIONS + ID + " TEXT);";

  private static final String CREATE_TABLE_EVALUATIONS = "CREATE TABLE IF NOT EXISTS " +
      EVALUATIONS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      RATE + " INTEGER, " +
      CRITERIA + ID + " TEXT);";


  private static final String CREATE_TABLE_CANDIDATES = "CREATE TABLE IF NOT EXISTS " +
      CANDIDATES + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      FIRST_NAME + " TEXT, " +
      LAST_NAME + " TEXT, " +
      EMAIL + " TEXT, " +
      LEVEL + " TEXT, " +
      CREATED + " TEXT, " +
      PHONE + " TEXT, " +
      SKYPE + " TEXT, " +
      CVS + ID + " TEXT, " +
      EXPERIENCE + " REAL, " +
      POSITION + ID + " TEXT, " +
      INTERVIEWS + ID + " TEXT, " +
      COMMENTS + ID + " TEXT, " +
      STATUS + " TEXT);";

  private static final String CREATE_TABLE_VACANCIES = "CREATE TABLE IF NOT EXISTS " +
      VACANCIES + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      CREATED + " TEXT, " +
      LAST_PUBLISHED + " TEXT, " +
      STATUS + " TEXT , " +
      NAME + " TEXT);";

  private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " +
      USERS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      FIRST_NAME + " TEXT, " +
      LAST_NAME + " TEXT, " +
      EMAIL + " TEXT, " +
      DEPARTMENT + ID + " TEXT, " +
      CREATED + " TEXT);";


  private static final String CREATE_TABLE_CRITERIAS = "CREATE TABLE IF NOT EXISTS " +
      CRITERIAS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      NAME + " TEXT);";

  private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE IF NOT EXISTS " +
      COMMENTS + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      CREATED_BY + ID + " TEXT, " +
      TEXT + " TEXT);";

  private static final String CREATE_TABLE_TEMPLATES = "CREATE TABLE IF NOT EXISTS " +
      TEMPLATES + "(" +
      ID + " INTEGER_PRIMARY_KEY, " +
      SUBJECT + " TEXT, " +
      TYPE + " TEXT, " +
      CONTENT + " TEXT, " +
      CREATED + " TEXT)";


  public SQLiteHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }


  @Override
  public void onCreate(SQLiteDatabase db) {

    db.execSQL(CREATE_TABLE_CANDIDATES);
    db.execSQL(CREATE_TABLE_CRITERIAS);
    db.execSQL(CREATE_TABLE_DEPARTMENTS);
    db.execSQL(CREATE_TABLE_EVALUATIONS);
    db.execSQL(CREATE_TABLE_INTERVIEWERS);
    db.execSQL(CREATE_TABLE_INTERVIEWS);
    db.execSQL(CREATE_TABLE_POSITIONS);
    db.execSQL(CREATE_TABLE_REQUESTS);
    db.execSQL(CREATE_TABLE_REQUIREMENTS);
    db.execSQL(CREATE_TABLE_VACANCIES);
    db.execSQL(CREATE_TABLE_USERS);
    db.execSQL(CREATE_TABLE_TEMPLATES);
    db.execSQL(CREATE_TABLE_CVC);
    db.execSQL(CREATE_TABLE_COMMENTS);

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("DROP TABLE IF EXISTS " + CANDIDATES);
    db.execSQL("DROP TABLE IF EXISTS " + CRITERIAS);
    db.execSQL("DROP TABLE IF EXISTS " + DEPARTMENTS);
    db.execSQL("DROP TABLE IF EXISTS " + EVALUATIONS);
    db.execSQL("DROP TABLE IF EXISTS " + INTERVIEWERS);
    db.execSQL("DROP TABLE IF EXISTS " + INTERVIEWS);
    db.execSQL("DROP TABLE IF EXISTS " + POSITIONS);
    db.execSQL("DROP TABLE IF EXISTS " + REQUESTS);
    db.execSQL("DROP TABLE IF EXISTS " + REQUIREMENTS);
    db.execSQL("DROP TABLE IF EXISTS " + VACANCIES);
    db.execSQL("DROP TABLE IF EXISTS " + USERS);
    db.execSQL("DROP TABLE IF EXISTS " + TEMPLATES);
    db.execSQL("DROP TABLE IF EXISTS " + CVS);
    db.execSQL("DROP TABLE IF EXISTS " + COMMENTS);
    onCreate(db);

  }

  public void saveRequests(List<Request> requests) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < requests.size(); i++) {
      Request request = requests.get(i);
      cv.put(ID, request.getId());
      if (request.getPosition() != null)
        cv.put(POSITION + ID, request.getPosition().getId());
      cv.put(COUNT, request.getCount());
      cv.put(CREATED, request.getCreated());
      cv.put(MODIFIED, request.getCreated());
      cv.put(STATUS, request.getStatus());
      if (request.getUserCreatedBy() != null)
        cv.put(CREATED_BY + ID, request.getUserCreatedBy().getId());

      // push requirements IDs to list as string type
      List<String> reqIds = new ArrayList<String>();
      if (request.getRequirementList() != null) {
        List<Requirement> requirements = request.getRequirementList();
        for (int j = 0; j < requirements.size(); j++) {
          reqIds.add(requirements.get(j).getId().toString());
        }
        // convert from List to String
        String stringReqList = Converter.convertListToString(reqIds);
        cv.put(REQUIREMENTS + ID, stringReqList);
      }
      int conflict = (int) db.insertWithOnConflict(REQUESTS, null,
          cv,
          SQLiteDatabase.CONFLICT_IGNORE);
      // if row is existed then update by id
      if (conflict < 0)
        db.update(REQUESTS, cv, ID + " = ?", new String[]{
            request.getId().toString()});
    }

    db.close();
  }

  public void saveDepartments(List<Department> departments) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < departments.size(); i++) {
      Department department = departments.get(i);

      cv.put(ID, department.getId());
      cv.put(NAME, department.getName());

      Cursor cursor = db.rawQuery("SELECT * FROM " + DEPARTMENTS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(department.getId())});
      if (cursor.getCount() > 0)
        db.update(DEPARTMENTS, cv, ID + " = ?", new String[]{
            department.getId().toString()});
      else
        db.insert(DEPARTMENTS, null,
            cv);
      // if row is existed then update by id

    }

    db.close();
  }

  public void savePositions(List<Position> positions) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < positions.size(); i++) {
      Position position = positions.get(i);

      cv.put(ID, position.getId());
      cv.put(NAME, position.getName());
      if (position.getDepartment() != null)
        cv.put(DEPARTMENT + ID, position.getDepartment().getId());

      Cursor cursor = db.rawQuery("SELECT * FROM " + POSITIONS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(position.getId())});
      if (cursor.getCount() > 0)
        db.update(POSITIONS, cv, ID + " = ?", new String[]{
            position.getId().toString()});
      else
        db.insert(POSITIONS, null,
            cv);
    }

    db.close();
  }

  public void saveRequirements(List<Requirement> requirements) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < requirements.size(); i++) {
      Requirement requirement = requirements.get(i);

      cv.put(ID, requirement.getId());
      cv.put(NAME, requirement.getName());
      cv.put(TYPE, requirement.getType());
      cv.put(DEPARTMENT + ID, requirement.getId());


      Cursor cursor = db.rawQuery("SELECT * FROM " + REQUIREMENTS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(requirement.getId())});
      if (cursor.getCount() > 0)
        db.update(REQUIREMENTS, cv, ID + " = ?", new String[]{
            requirement.getId().toString()});
      else
        db.insert(REQUIREMENTS, null,
            cv);
    }

    db.close();
  }

  public void saveCvs(List<Cv> cvs) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();

    for (int i = 0; i < cvs.size(); i++) {
      Cv cv = cvs.get(i);

      contentValues.put(ID, cv.getId());
      contentValues.put(CREATED, cv.getCreated());
      contentValues.put(URL, cv.getLink());


      Cursor cursor = db.rawQuery("SELECT * FROM " + CVS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(cv.getId())});
      if (cursor.getCount() > 0)
        db.update(CVS, contentValues, ID + " = ?", new String[]{
            cv.getId().toString()});
      else
        db.insert(CVS, null,
            contentValues);
    }

    db.close();
  }

  public void saveInterviews(List<Interview> interviews) {

    for (int i = 0; i < interviews.size(); i++) {
      saveDepartments(new ArrayList<Department>(Arrays.asList(interviews.get(i)
          .getCandidate().getPosition()
          .getDepartment())));
      savePositions(new ArrayList<Position>(Arrays.asList(interviews.get(i)
          .getCandidate().getPosition())));
      saveCandidates(new ArrayList<Candidate>(Arrays.asList(interviews.get(i)
          .getCandidate())));
      for (int j = 0; j < interviews.get(i).getInterviewersList().size(); j++) {
        saveEvaluations(interviews.get(i)
            .getInterviewersList().get(j).getEvaluaionList());
      }
      saveInterviewers(interviews.get(i).getInterviewersList());
    }

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < interviews.size(); i++) {
      Interview interview = interviews.get(i);
      cv.put(ID, interview.getId());
      cv.put(DATE, interview.getDate());
      cv.put(STATUS, interview.getStatus());
      if (interview.getCandidate() != null)
        cv.put(CANDIDATE + ID, interview.getCandidate().getId());
      // push inteviewers IDs to list as string type
      List<String> interviewersIds = new ArrayList<String>();
      if (interview.getInterviewersList() != null) {
        List<Interviewer> interviewers = interview.getInterviewersList();
        for (int j = 0; j < interviewers.size(); j++) {
          interviewersIds.add(interviewers.get(j).getId().toString());
        }
        // convert from List to String
        if (interviewersIds.size() > 0) {
          String stringInterviewersList = Converter.convertListToString(interviewersIds);

          cv.put(INTERVIEWERS + ID, stringInterviewersList);
        }
      }


      Cursor cursor = db.rawQuery("SELECT * FROM " + INTERVIEWS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(interview.getId())});
      if (cursor.getCount() > 0)
        db.update(INTERVIEWS, cv, ID + " = ?", new String[]{
            interview.getId().toString()});
      else
        db.insert(INTERVIEWS, null,
            cv);
    }

    db.close();
  }

  public void saveInterviewers(List<Interviewer> interviewers) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < interviewers.size(); i++) {
      Interviewer interviewer = interviewers.get(i);

      cv.put(ID, interviewer.getId());
      if (interviewer.getUser() != null)
        cv.put(USER + ID, interviewer.getUser().getId());
      cv.put(COMMENT, interviewer.getComment());
      // push evaluations IDs to list as string type
      List<String> evaluationsIds = new ArrayList<String>();
      if (interviewer.getEvaluaionList() != null) {
        List<Evaluation> evaluations = interviewer.getEvaluaionList();
        for (int j = 0; j < evaluations.size(); j++) {
          evaluationsIds.add(evaluations.get(j).getId().toString());
        }
        // convert from List to String
        String stringEvaluationsList = Converter.convertListToString(evaluationsIds);
        cv.put(EVALUATIONS + ID, stringEvaluationsList);
      }


      Cursor cursor = db.rawQuery("SELECT * FROM " + INTERVIEWERS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(interviewer.getId())});
      if (cursor.getCount() > 0)
        db.update(INTERVIEWERS, cv, ID + " = ?", new String[]{
            interviewer.getId().toString()});
      else
        db.insert(INTERVIEWERS, null,
            cv);
    }

    db.close();
  }

  public void saveEvaluations(List<Evaluation> evaluations) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < evaluations.size(); i++) {
      Evaluation evaluation = evaluations.get(i);

      cv.put(ID, evaluation.getId());
      cv.put(RATE, evaluation.getRate());
      cv.put(CRITERIA + ID, evaluation.getCriteria().getId());


      Cursor cursor = db.rawQuery("SELECT * FROM " + EVALUATIONS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(evaluation.getId())});
      if (cursor.getCount() > 0)
        db.update(EVALUATIONS, cv, ID + " = ?", new String[]{
            evaluation.getId().toString()});
      else
        db.insert(EVALUATIONS, null,
            cv);

    }

    db.close();
  }

  public void saveCandidates(List<Candidate> candidates) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < candidates.size(); i++) {
      Candidate candidate = candidates.get(i);

      cv.put(ID, candidate.getId());
      cv.put(FIRST_NAME, candidate.getFirstName());
      cv.put(LAST_NAME, candidate.getLastName());
      cv.put(EMAIL, candidate.getEmail());
      cv.put(LEVEL, candidate.getLevel());
      cv.put(PHONE, candidate.getPhone());
      cv.put(STATUS, candidate.getStatus());
      cv.put(EXPERIENCE, candidate.getExperience());
      cv.put(POSITION + ID, candidate.getPosition().getId());
      cv.put(CREATED, candidate.getCreated());
      // CVS
      List<String> cvsIds = new ArrayList<String>();
      if (candidate.getCvs() != null) {
        List<Cv> cvs = candidate.getCvs();
        for (int j = 0; j < cvs.size(); j++) {
          cvsIds.add(cvs.get(j).getId().toString());
        }
        // convert from List to String
        String stringCvsList = Converter.convertListToString(cvsIds);
        cv.put(EVALUATIONS + ID, stringCvsList);
      }
      //
      // Interviews
      List<String> interviewsIds = new ArrayList<String>();
      if (candidate.getInterviews() != null) {
        List<Interview> interviews = candidate.getInterviews();
        for (int j = 0; j < interviews.size(); j++) {
          interviewsIds.add(interviews.get(j).getId().toString());
        }
        // convert from List to String
        String stringInterviewsList = Converter.convertListToString(interviewsIds);
        cv.put(EVALUATIONS + ID, stringInterviewsList);
      }
      //
      // Comments
      List<String> commentsIds = new ArrayList<String>();
      if (candidate.getComments() != null) {
        List<Comment> comments = candidate.getComments();
        for (int j = 0; j < comments.size(); j++) {
          commentsIds.add(comments.get(j).getId().toString());
        }
        // convert from List to String
        String stringCommentsList = Converter.convertListToString(commentsIds);
        cv.put(COMMENTS + ID, stringCommentsList);
      }
      //


      Cursor cursor = db.rawQuery("SELECT * FROM " + CANDIDATES + " WHERE " + ID + " =?", new
          String[]{String.valueOf(candidate.getId())});
      if (cursor.getCount() > 0)
        db.update(CANDIDATES, cv, ID + " = ?", new String[]{
            candidate.getId().toString()});
      else
        db.insert(CANDIDATES, null,
            cv);

    }

    db.close();
  }

  public void saveVacancies(List<Vacancy> vacancies) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < vacancies.size(); i++) {
      Vacancy vacancy = vacancies.get(i);

      cv.put(ID, vacancy.getId());
      cv.put(NAME, vacancy.getName());
      cv.put(LAST_PUBLISHED, vacancy.getDateLastPublished());
      cv.put(CREATED, vacancy.getDateCreated());

      Cursor cursor = db.rawQuery("SELECT * FROM " + VACANCIES + " WHERE " + ID + " =?", new
          String[]{String.valueOf(vacancy.getId())});
      if (cursor.getCount() > 0)
        db.update(VACANCIES, cv, ID + " = ?", new String[]{
            vacancy.getId().toString()});
      else
        db.insert(VACANCIES, null,
            cv);
    }

    db.close();
  }

  public void saveUsers(List<User> users) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < users.size(); i++) {
      User user = users.get(i);

      cv.put(ID, user.getId());
      cv.put(FIRST_NAME, user.getFirstName());
      cv.put(LAST_NAME, user.getLastName());
      cv.put(CREATED, user.getCreated());

      if (user.getDepartment() != null)
        cv.put(DEPARTMENT + ID, user.getDepartment().getId());


      Cursor cursor = db.rawQuery("SELECT * FROM " + USERS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(user.getId())});
      if (cursor.getCount() > 0)
        db.update(USERS, cv, ID + " = ?", new String[]{
            user.getId().toString()});
      else
        db.insert(USERS, null,
            cv);
    }

    db.close();
  }

  public void saveCriterias(List<Criteria> criterias) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < criterias.size(); i++) {
      Criteria criteria = criterias.get(i);

      cv.put(ID, criteria.getId());
      cv.put(NAME, criteria.getName());


      Cursor cursor = db.rawQuery("SELECT * FROM " + CRITERIAS + " WHERE " + ID + " =?", new
          String[]{String.valueOf(criteria.getId())});
      if (cursor.getCount() > 0)
        db.update(CRITERIAS, cv, ID + " = ?", new String[]{
            criteria.getId().toString()});
      else
        db.insert(CRITERIAS, null,
            cv);
    }

    db.close();
  }

  public void saveTemplates(List<Template> templates) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    for (int i = 0; i < templates.size(); i++) {
      Template template = templates.get(i);

      cv.put(ID, template.getId());
      cv.put(SUBJECT, template.getSubject());
      cv.put(TYPE, template.getType());
      cv.put(CONTENT, template.getContent());
      cv.put(CREATED, template.getCreated());


      Cursor cursor = db.rawQuery("SELECT * FROM " + TEMPLATES + " WHERE " + ID + " =?", new
          String[]{String.valueOf(template.getId())});
      if (cursor.getCount() > 0)
        db.update(TEMPLATES, cv, ID + " = ?", new String[]{
            template.getId().toString()});
      else
        db.insert(TEMPLATES, null,
            cv);
    }

    db.close();
  }

  //GET BY ID
  public Department getDepartment(String departmentId) {
    Department department = new Department();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor departmentCursor = dbReadable.rawQuery(
        "SELECT * FROM " + DEPARTMENTS + " WHERE " + ID + "=?",
        new String[]{departmentId}
    );
    if (departmentCursor.moveToFirst()) {
      do {
        department.setId(departmentCursor.getInt(departmentCursor.getColumnIndex(ID)));
        department.setName(departmentCursor.getString(departmentCursor.getColumnIndex(NAME)));

      } while (departmentCursor.moveToNext());
    }

    departmentCursor.close();
    dbReadable.close();

    return department;
  }

  public Cv getCv(String cvId) {
    Cv cv = new Cv();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor cvCursor = dbReadable.rawQuery(
        "SELECT * FROM " + CVS + " WHERE " + ID + "=?",
        new String[]{cvId}
    );
    if (cvCursor.moveToFirst()) {
      do {
        cv.setId(cvCursor.getInt(cvCursor.getColumnIndex(ID)));
        cv.setLink(cvCursor.getString(cvCursor.getColumnIndex(URL)));
        cv.setCreated(cvCursor.getString(cvCursor.getColumnIndex(CREATED)));
      } while (cvCursor.moveToNext());
    }

    cvCursor.close();
    dbReadable.close();

    return cv;
  }

  public Criteria getCriteria(String criteriaId) {
    Criteria criteria = new Criteria();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor criteriaCursor = dbReadable.rawQuery(
        "SELECT * FROM " + CRITERIAS + " WHERE " + ID + "=?",
        new String[]{criteriaId}
    );
    if (criteriaCursor.moveToFirst()) {
      do {
        criteria.setId(criteriaCursor.getInt(criteriaCursor.getColumnIndex(ID)));
        criteria.setName(criteriaCursor.getString(criteriaCursor.getColumnIndex(NAME)));

      } while (criteriaCursor.moveToNext());
    }

    criteriaCursor.close();
    dbReadable.close();

    return criteria;
  }

  public Template getTemplate(String templateId) {
    Template template = new Template();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor templateCursor = dbReadable.rawQuery(
        "SELECT * FROM " + TEMPLATES + " WHERE " + ID + "=?",
        new String[]{templateId}
    );
    if (templateCursor.moveToFirst()) {
      do {
        template.setId(templateCursor.getInt(templateCursor.getColumnIndex(ID)));
        template.setSubject(templateCursor.getString(templateCursor.getColumnIndex(SUBJECT)));
        template.setContent(templateCursor.getString(templateCursor.getColumnIndex(CONTENT)));
        template.setCreated(templateCursor.getString(templateCursor.getColumnIndex(CREATED)));
        template.setType(templateCursor.getString(templateCursor.getColumnIndex(TYPE)));
      } while (templateCursor.moveToNext());
    }

    templateCursor.close();
    dbReadable.close();

    return template;
  }

  public Position getPosition(String positionId) {
    Position position = new Position();

    SQLiteDatabase dbReadable = this.getReadableDatabase();


    Cursor positionCursor = dbReadable.rawQuery(
        "SELECT * FROM " + POSITIONS + " WHERE " + ID + "=?",
        new String[]{positionId}
    );
    if (positionCursor.moveToFirst()) {
      do {
        position.setId(positionCursor.getInt(positionCursor.getColumnIndex(ID)));
        position.setName(positionCursor.getString(positionCursor.getColumnIndex(NAME)));
        position.setDepartment(getDepartment(positionCursor.getString(positionCursor
            .getColumnIndex(DEPARTMENT + ID))));
      } while (positionCursor.moveToNext());
    }

    positionCursor.close();
    dbReadable.close();

    return position;
  }

  public Requirement getRequirement(String requirementId) {
    Requirement requirement = new Requirement();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor requirementCursor = dbReadable.rawQuery(
        "SELECT * FROM " + REQUIREMENTS + " WHERE " + ID + "=?",
        new String[]{requirementId}
    );
    if (requirementCursor.moveToFirst()) {
      do {
        requirement.setId(requirementCursor.getInt(requirementCursor.getColumnIndex(ID)));
        requirement.setName(requirementCursor.getString(requirementCursor.getColumnIndex(NAME)));
        requirement.setDepartment(requirementCursor.getInt(requirementCursor
            .getColumnIndex(DEPARTMENT + ID)));
        requirement.setType(requirementCursor.getString(requirementCursor.getColumnIndex(TYPE)));
      } while (requirementCursor.moveToNext());
    }

    requirementCursor.close();
    dbReadable.close();

    return requirement;
  }

  public Vacancy getVacancy(String vacancyId) {
    Vacancy vacancy = new Vacancy();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor vacancyCursor = dbReadable.rawQuery(
        "SELECT * FROM " + VACANCIES + " WHERE " + ID + "=?",
        new String[]{vacancyId}
    );
    if (vacancyCursor.moveToFirst()) {
      do {
        vacancy.setId(vacancyCursor.getInt(vacancyCursor.getColumnIndex(ID)));
        vacancy.setName(vacancyCursor.getString(vacancyCursor.getColumnIndex(NAME)));
        vacancy.setStatus(vacancyCursor.getString(vacancyCursor.getColumnIndex(STATUS)));
        vacancy.setDateCreated(vacancyCursor.getString(vacancyCursor.getColumnIndex(CREATED)));
        vacancy.setDateLastPublished(vacancyCursor.getString(vacancyCursor.getColumnIndex(LAST_PUBLISHED)));
      } while (vacancyCursor.moveToNext());
    }

    vacancyCursor.close();
    dbReadable.close();

    return vacancy;
  }

  public User getUser(String userId) {
    User user = new User();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor userCursor = dbReadable.rawQuery(
        "SELECT * FROM " + USERS + " WHERE " + ID + "=?",
        new String[]{userId}
    );
    if (userCursor.moveToFirst()) {
      do {
        user.setId(userCursor.getInt(userCursor.getColumnIndex(ID)));
        user.setDepartment(getDepartment(userCursor.getString(userCursor
            .getColumnIndex(DEPARTMENT + ID))));
        user.setCreated(userCursor.getString(userCursor.getColumnIndex(CREATED)));
        user.setFirstName(userCursor.getString(userCursor.getColumnIndex(FIRST_NAME)));
        user.setEmail(userCursor.getString(userCursor.getColumnIndex(EMAIL)));
        user.setLastName(userCursor.getString(userCursor.getColumnIndex(LAST_NAME)));
      } while (userCursor.moveToNext());
    }

    userCursor.close();
    dbReadable.close();

    return user;
  }

  public Request getRequest(String requestId) {
    Request request = new Request();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor requestCursor = dbReadable.rawQuery(
        "SELECT * FROM " + REQUESTS + " WHERE " + ID + "=?",
        new String[]{requestId}
    );
    if (requestCursor.moveToFirst()) {
      do {
        request.setId(requestCursor.getInt(requestCursor.getColumnIndex(ID)));
        request.setPosition(getPosition(requestCursor.getString(requestCursor
            .getColumnIndex(POSITION + ID))));
        request.setUserCreatedBy(getUser(requestCursor.getString(requestCursor
            .getColumnIndex(CREATED_BY + ID))));
        request.setCreated(requestCursor.getString(requestCursor.getColumnIndex(CREATED)));
        request.setCount(requestCursor.getInt(requestCursor.getColumnIndex(COUNT)));
        request.setModified(requestCursor.getString(requestCursor.getColumnIndex(MODIFIED)));
        request.setStatus(requestCursor.getString(requestCursor.getColumnIndex(STATUS)));

        if (requestCursor.getString(requestCursor.getColumnIndex(REQUIREMENTS+ ID)) != null) {
          List<String> reqStringList = Converter.convertStringToList(requestCursor.getString
              (requestCursor
                  .getColumnIndex
                      (REQUIREMENTS + ID)));

          request.setRequirementList(getRequirements(reqStringList));
        }
      } while (requestCursor.moveToNext());
    }

    requestCursor.close();
    dbReadable.close();

    return request;
  }

  public Evaluation getEvaluation(String evaluationId) {
    Evaluation evaluation = new Evaluation();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor evaluationCursor = dbReadable.rawQuery(
        "SELECT * FROM " + EVALUATIONS + " WHERE " + ID + "=?",
        new String[]{evaluationId}
    );
    if (evaluationCursor.moveToFirst()) {
      do {
        evaluation.setId(evaluationCursor.getInt(evaluationCursor.getColumnIndex(ID)));
        evaluation.setRate(evaluationCursor.getInt(evaluationCursor.getColumnIndex(RATE)));
        evaluation.setCriteria(getCriteria(evaluationCursor.getString(evaluationCursor
            .getColumnIndex(CRITERIA + ID))));

      } while (evaluationCursor.moveToNext());
    }

    evaluationCursor.close();
    dbReadable.close();

    return evaluation;
  }

  public Interviewer getInterviewer(String interviewerId) {
    Interviewer interviewer = new Interviewer();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor interviewerCursor = dbReadable.rawQuery(
        "SELECT * FROM " + INTERVIEWERS + " WHERE " + ID + "=?",
        new String[]{interviewerId}
    );
    if (interviewerCursor.moveToFirst()) {
      do {
        interviewer.setId(interviewerCursor.getInt(interviewerCursor.getColumnIndex(ID)));
        interviewer.setComment(interviewerCursor.getString(interviewerCursor.getColumnIndex
            (COMMENT)));
        interviewer.setUser(getUser(interviewerCursor.getString(interviewerCursor
            .getColumnIndex(USER + ID))));

        if (interviewerCursor.getString(interviewerCursor.getColumnIndex(EVALUATIONS + ID)) != null) {
          List<String> evalStringList = Converter.convertStringToList(interviewerCursor.getString
              (interviewerCursor
                  .getColumnIndex
                      (EVALUATIONS + ID)));
          interviewer.setEvaluaionList(getEvaluations(evalStringList));
        }
      } while (interviewerCursor.moveToNext());
    }

    interviewerCursor.close();
    dbReadable.close();

    return interviewer;
  }

  public Comment getComment(String commentId) {
    Comment comment = new Comment();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor commentCursor = dbReadable.rawQuery(
        "SELECT * FROM " + COMMENTS + " WHERE " + ID + "=?",
        new String[]{commentId}
    );
    if (commentCursor.moveToFirst()) {
      do {
        comment.setId(commentCursor.getInt(commentCursor.getColumnIndex(ID)));
        comment.setText(commentCursor.getString(commentCursor.getColumnIndex(TEXT)));
        comment.setCreatedBy(getUser(commentCursor.getString(commentCursor
            .getColumnIndex(CREATED_BY + ID))));
      } while (commentCursor.moveToNext());
    }

    commentCursor.close();
    dbReadable.close();

    return comment;
  }

  public Candidate getCandidate(String candidateId) {
    Candidate candidate = new Candidate();

    SQLiteDatabase dbReadable = this.getReadableDatabase();
    Cursor candidateCursor = dbReadable.rawQuery(
        "SELECT * FROM " + CANDIDATES + " WHERE " + ID + " = ?",
        new String[]{candidateId}
    );

    if (candidateCursor.moveToFirst()) {
      do {
        candidate.setId(candidateCursor.getInt(candidateCursor.getColumnIndex(ID)));
        candidate.setEmail(candidateCursor.getString(candidateCursor.getColumnIndex(EMAIL)));
        candidate.setExperience(candidateCursor.getFloat(candidateCursor.getColumnIndex(EXPERIENCE)));
        candidate.setFirstName(candidateCursor.getString(candidateCursor.getColumnIndex(FIRST_NAME)));
        candidate.setLastName(candidateCursor.getString(candidateCursor.getColumnIndex(LAST_NAME)));
        candidate.setLevel(candidateCursor.getString(candidateCursor.getColumnIndex(LEVEL)));
        candidate.setPhone(candidateCursor.getString(candidateCursor.getColumnIndex(PHONE)));
        candidate.setStatus(candidateCursor.getString(candidateCursor.getColumnIndex(STATUS)));
        candidate.setPosition(getPosition(candidateCursor.getString(candidateCursor
            .getColumnIndex(POSITION + ID))));
        candidate.setSkype(candidateCursor.getString(candidateCursor.getColumnIndex(SKYPE)));
        candidate.setCreated(candidateCursor.getString(candidateCursor.getColumnIndex(CREATED)));

        if (candidateCursor.getString(candidateCursor.getColumnIndex(COMMENTS + ID)) != null) {
          List<String> commentStringList = Converter.convertStringToList(candidateCursor.getString
              (candidateCursor
                  .getColumnIndex
                      (COMMENTS + ID)));
          candidate.setComments(getComments(commentStringList));
        }

        if (candidateCursor.getString(candidateCursor.getColumnIndex(CVS + ID)) != null) {
          List<String> cvStringList = Converter.convertStringToList(candidateCursor.getString
              (candidateCursor
                  .getColumnIndex
                      (CVS + ID)));
          candidate.setCvs(getCvs(cvStringList));
        }

        if (candidateCursor.getString(candidateCursor.getColumnIndex(INTERVIEWS + ID)) != null) {
          List<String> interviewStringList = Converter.convertStringToList(candidateCursor.getString
              (candidateCursor
                  .getColumnIndex
                      (INTERVIEWS + ID)));
          candidate.setInterviews(getInterviews(interviewStringList));
        }

      } while (candidateCursor.moveToNext());
    }

    candidateCursor.close();
    dbReadable.close();

    return candidate;
  }

  public Interview getInterview(String interviewId) {
    Interview interview = new Interview();

    SQLiteDatabase dbReadable = this.getReadableDatabase();

    Cursor interviewCursor = dbReadable.rawQuery(
        "SELECT * FROM " + INTERVIEWS + " WHERE " + ID + "=?",
        new String[]{interviewId}
    );

    if (interviewCursor.moveToFirst()) {
      do {
        interview.setId(interviewCursor.getInt(interviewCursor.getColumnIndex(ID)));
        interview.setCandidate(getCandidate(interviewCursor.getString(interviewCursor.getColumnIndex
            (CANDIDATE + ID))));
        interview.setDate(interviewCursor.getString(interviewCursor.getColumnIndex(DATE)));
        interview.setStatus(interviewCursor.getString(interviewCursor.getColumnIndex(STATUS)));

        if (interviewCursor.getString(interviewCursor.getColumnIndex(INTERVIEWERS + ID)) != null) {
          List<String> interviewerStringList = Converter.convertStringToList(interviewCursor.getString
              (interviewCursor
                  .getColumnIndex
                      (INTERVIEWERS + ID)));
          interview.setInterviewersList(getInterviewers(interviewerStringList));
        }

      } while (interviewCursor.moveToNext());
    }

    interviewCursor.close();
    dbReadable.close();

    return interview;
  }

  // GET LIST OF MODEL

  private List<Requirement> getRequirements(List<String> reqStringList) {

    List<Requirement> requirements = new ArrayList<Requirement>();
    for (int i = 0; i < reqStringList.size(); i++)
      requirements.add(getRequirement(reqStringList.get(i)));

    return requirements;
  }

  private List<Criteria> getCriterias(List<String> criteriaStringList) {

    List<Criteria> criterias = new ArrayList<Criteria>();
    for (int i = 0; i < criteriaStringList.size(); i++)
      criterias.add(getCriteria(criteriaStringList.get(i)));

    return criterias;
  }

  private List<Evaluation> getEvaluations(List<String> evaluationStringList) {

    List<Evaluation> evaluations = new ArrayList<Evaluation>();
    for (int i = 0; i < evaluationStringList.size(); i++)
      evaluations.add(getEvaluation(evaluationStringList.get(i)));

    return evaluations;
  }

  private List<Comment> getComments(List<String> commentStringList) {

    List<Comment> comments = new ArrayList<Comment>();
    for (int i = 0; i < commentStringList.size(); i++)
      comments.add(getComment(commentStringList.get(i)));

    return comments;
  }

  private List<Cv> getCvs(List<String> cvStringList) {

    List<Cv> cvs = new ArrayList<Cv>();
    for (int i = 0; i < cvStringList.size(); i++)
      cvs.add(getCv(cvStringList.get(i)));

    return cvs;
  }

  private List<Interview> getInterviews(List<String> interviewStringList) {

    List<Interview> interviews = new ArrayList<Interview>();
    for (int i = 0; i < interviewStringList.size(); i++)
      interviews.add(getInterview(interviewStringList.get(i)));

    return interviews;
  }

  private List<Position> getPositions(List<String> positionStringList) {

    List<Position> positions = new ArrayList<Position>();
    for (int i = 0; i < positionStringList.size(); i++)
      positions.add(getPosition(positionStringList.get(i)));

    return positions;
  }

  private List<Interviewer> getInterviewers(List<String> interviewerStringList) {

    List<Interviewer> interviewers = new ArrayList<Interviewer>();
    for (int i = 0; i < interviewerStringList.size(); i++)
      interviewers.add(getInterviewer(interviewerStringList.get(i)));

    return interviewers;
  }


// GET MODELS

  public ArrayList<Request> getRequests() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Request> requests = new ArrayList<>();
    Cursor cursor = db.query(REQUESTS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int requestIndex = cursor.getColumnIndex(ID);
      int positionIdIndex = cursor.getColumnIndex(POSITION + ID);
      int requirementsIdIndex = cursor.getColumnIndex(REQUIREMENTS + ID);
      int countIndex = cursor.getColumnIndex(COUNT);
      int statusIndex = cursor.getColumnIndex(STATUS);
      int createdIndex = cursor.getColumnIndex(CREATED);
      int createdByIndex = cursor.getColumnIndex(CREATED_BY + ID);
      int modifiedIndex = cursor.getColumnIndex(MODIFIED);

      do {
        Request request = new Request();

        int id = cursor.getInt(requestIndex);
        String positionId = cursor.getString(positionIdIndex);
        String requirementsId = cursor.getString(requirementsIdIndex);
        int count = cursor.getInt(countIndex);
        String status = cursor.getString(statusIndex);
        String created = cursor.getString(createdIndex);
        String createdBy = cursor.getString(createdByIndex);
        String modified = cursor.getString(modifiedIndex);


        request.setCount(count);
        request.setCreated(created);
        if (positionId != null)
          request.setPosition(getPosition(positionId));
        if (requirementsId != null)
          request.setRequirementList(getRequirements(Converter.convertStringToList(requirementsId)));
        request.setStatus(status);
        if (createdBy != null)
          request.setUserCreatedBy(getUser(createdBy));
        request.setId(id);
        request.setModified(modified);

        requests.add(request);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return requests;
  }

  public ArrayList<Position> getPositions() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Position> positions = new ArrayList<>();
    Cursor cursor = db.query(POSITIONS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int departmentIdIndex = cursor.getColumnIndex(DEPARTMENT + ID);
      int nameIndex = cursor.getColumnIndex(NAME);

      do {
        Position position = new Position();

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);
        String departmentId = cursor.getString(departmentIdIndex);


        position.setId(id);
        position.setName(name);
        if (departmentId != null)
          position.setDepartment(getDepartment(departmentId));

        positions.add(position);
      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return positions;
  }

  public ArrayList<Requirement> getRequirements() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Requirement> requirements = new ArrayList<>();
    Cursor cursor = db.query(REQUIREMENTS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int departmentIdIndex = cursor.getColumnIndex(DEPARTMENT + ID);
      int nameIndex = cursor.getColumnIndex(NAME);
      int typeIndex = cursor.getColumnIndex(TYPE);
      do {
        Requirement requirement = new Requirement();

        int id = cursor.getInt(idIndex);
        int departmentId = cursor.getInt(departmentIdIndex);
        String name = cursor.getString(nameIndex);
        String type = cursor.getString(typeIndex);

        requirement.setDepartment(departmentId);
        requirement.setId(id);
        requirement.setName(name);
        requirement.setType(type);

        requirements.add(requirement);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return requirements;
  }

  public ArrayList<Cv> getCvs() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Cv> cvs = new ArrayList<>();
    Cursor cursor = db.query(CVS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int urlIndex = cursor.getColumnIndex(URL);
      int createdIndex = cursor.getColumnIndex(CREATED);

      do {
        Cv cv = new Cv();

        int id = cursor.getInt(idIndex);
        String url = cursor.getString(urlIndex);
        String created = cursor.getString(createdIndex);

        cv.setCreated(created);
        cv.setId(id);
        cv.setLink(url);

        cvs.add(cv);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return cvs;
  }

  public ArrayList<Interview> getInterviews() {

    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Interview> interviews = new ArrayList<>();
    Cursor cursor = db.query(INTERVIEWS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int dateIndex = cursor.getColumnIndex(DATE);
      int interviewersIdIndex = cursor.getColumnIndex(INTERVIEWERS + ID);
      int candidateIdIndex = cursor.getColumnIndex(CANDIDATE + ID);
      int statusIndex = cursor.getColumnIndex(STATUS);

      do {
        Interview interview = new Interview();

        int id = cursor.getInt(idIndex);
        String date = cursor.getString(dateIndex);
        String interviewersId = cursor.getString(interviewersIdIndex);
        String candidateId = cursor.getString(candidateIdIndex);
        String status = cursor.getString(statusIndex);


        interview.setId(id);
        interview.setDate(date);
        interview.setStatus(status);

        if (candidateId != null)
          interview.setCandidate(getCandidate(candidateId));

        if (interviewersId != null)
          interview.setInterviewersList(getInterviewers(Converter.convertStringToList
              (interviewersId)));

        interviews.add(interview);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return interviews;
  }

  public ArrayList<Interviewer> getInterviewers() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Interviewer> interviewers = new ArrayList<>();
    Cursor cursor = db.query(INTERVIEWERS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int userIdIndex = cursor.getColumnIndex(USER + ID);
      int commentIndex = cursor.getColumnIndex(COMMENT);
      int evaluationsIndex = cursor.getColumnIndex(EVALUATIONS + ID);

      do {
        Interviewer interviewer = new Interviewer();

        int id = cursor.getInt(idIndex);
        String userId = cursor.getString(userIdIndex);
        String comment = cursor.getString(commentIndex);
        String evaluationsIds = cursor.getString(evaluationsIndex);

        if (evaluationsIds != null)
          interviewer.setEvaluaionList(getEvaluations(Converter.convertStringToList
              (evaluationsIds)));
        if (userId != null)
          interviewer.setUser(getUser(userId));
        interviewer.setComment(comment);
        interviewer.setId(id);

        interviewers.add(interviewer);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return interviewers;
  }

  public ArrayList<Evaluation> getEvaluations() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Evaluation> evaluations = new ArrayList<>();
    Cursor cursor = db.query(EVALUATIONS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int rateIndex = cursor.getColumnIndex(RATE);
      int criteriaIdIndex = cursor.getColumnIndex(CRITERIAS + ID);


      do {
        Evaluation evaluation = new Evaluation();

        int id = cursor.getInt(idIndex);
        int rate = cursor.getInt(rateIndex);
        String criteriaId = cursor.getString(criteriaIdIndex);


        evaluation.setId(id);
        evaluation.setRate(rate);
        if (criteriaId != null)
          evaluation.setCriteria(getCriteria(criteriaId));

        evaluations.add(evaluation);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return evaluations;
  }

  public ArrayList<Candidate> getCandidates() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Candidate> candidates = new ArrayList<>();
    Cursor cursor = db.query(CANDIDATES, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int firstNameIndex = cursor.getColumnIndex(FIRST_NAME);
      int lastNameIndex = cursor.getColumnIndex(LAST_NAME);
      int emailIndex = cursor.getColumnIndex(EMAIL);
      int levelIndex = cursor.getColumnIndex(LEVEL);
      int phoneIndex = cursor.getColumnIndex(PHONE);
      int cvsIdIndex = cursor.getColumnIndex(CVS + ID);
      int expIndex = cursor.getColumnIndex(EXPERIENCE);
      int positionIdIndex = cursor.getColumnIndex(POSITION + ID);
      int interviewsIdIndex = cursor.getColumnIndex(INTERVIEWERS + ID);
      int commentsIdIndex = cursor.getColumnIndex(COMMENTS);
      int statusIndex = cursor.getColumnIndex(STATUS);
      int createdIndex = cursor.getColumnIndex(CREATED);

      do {

        Candidate candidate = new Candidate();

        int id = cursor.getInt(idIndex);
        String firstName = cursor.getString(firstNameIndex);
        String lastName = cursor.getString(lastNameIndex);
        String email = cursor.getString(emailIndex);
        String status = cursor.getString(statusIndex);
        String phone = cursor.getString(phoneIndex);
        String cvsId = cursor.getString(cvsIdIndex);
        float exp = cursor.getFloat(expIndex);
        String positionId = cursor.getString(positionIdIndex);
        String level = cursor.getString(levelIndex);
        String interviewsId = cursor.getString(interviewsIdIndex);
        String commentsId = cursor.getString(commentsIdIndex);
        String created = cursor.getString(createdIndex);

        if (interviewsId != null)
          candidate.setInterviews(getInterviews(Converter.convertStringToList(interviewsId)));
        if (positionId != null)
          candidate.setPosition(getPosition(positionId));
        if (cvsId != null)
          candidate.setCvs(getCvs(Converter.convertStringToList(cvsId)));
        if (commentsId != null)
          candidate.setComments(getComments(Converter.convertStringToList(commentsId)));
        candidate.setStatus(status);
        candidate.setPhone(phone);
        candidate.setLevel(level);
        candidate.setLastName(lastName);
        candidate.setFirstName(firstName);
        candidate.setEmail(email);
        candidate.setExperience(exp);
        candidate.setId(id);
        candidate.setCreated(created);

        candidates.add(candidate);
      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return candidates;
  }

  public ArrayList<Vacancy> getVacancies() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Vacancy> vacancies = new ArrayList<>();
    Cursor cursor = db.query(VACANCIES, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int createdIndex = cursor.getColumnIndex(CREATED);
      int lastPublishedIndex = cursor.getColumnIndex(LAST_PUBLISHED);
      int nameIndex = cursor.getColumnIndex(NAME);
      int statusIndex = cursor.getColumnIndex(STATUS);

      do {
        Vacancy vacancy = new Vacancy();

        int id = cursor.getInt(idIndex);
        String created = cursor.getString(createdIndex);
        String lastPublished = cursor.getString(lastPublishedIndex);
        String status = cursor.getString(statusIndex);
        String name = cursor.getString(nameIndex);

        vacancy.setId(id);
        vacancy.setDateLastPublished(lastPublished);
        vacancy.setDateCreated(created);
        vacancy.setStatus(status);
        vacancy.setName(name);

        vacancies.add(vacancy);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return vacancies;
  }

  public ArrayList<User> getUsers() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<User> users = new ArrayList<>();
    Cursor cursor = db.query(USERS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int firstNameIndex = cursor.getColumnIndex(FIRST_NAME);
      int lastNameIndex = cursor.getColumnIndex(LAST_NAME);
      int emailIndex = cursor.getColumnIndex(EMAIL);
      int departmentIdIndex = cursor.getColumnIndex(DEPARTMENT + ID);
      int createdIndex = cursor.getColumnIndex(CREATED);


      do {
        User user = new User();

        int id = cursor.getInt(idIndex);
        String firstName = cursor.getString(firstNameIndex);
        String lastName = cursor.getString(lastNameIndex);
        String email = cursor.getString(emailIndex);
        String departmentId = cursor.getString(departmentIdIndex);
        String created = cursor.getString(createdIndex);

        user.setId(id);
        user.setLastName(lastName);
        if (departmentId != null)
          user.setDepartment(getDepartment(departmentId));
        user.setCreated(created);
        user.setFirstName(firstName);
        user.setEmail(email);

        users.add(user);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return users;
  }

  public ArrayList<Criteria> getCriterias() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Criteria> criterias = new ArrayList<>();
    Cursor cursor = db.query(CRITERIAS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int nameIndex = cursor.getColumnIndex(NAME);

      do {
        Criteria criteria = new Criteria();

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);

        criteria.setId(id);
        criteria.setName(name);

        criterias.add(criteria);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return criterias;
  }

  public ArrayList<Comment> getComments() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Comment> comments = new ArrayList<>();
    Cursor cursor = db.query(COMMENTS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int createdByIdIndex = cursor.getColumnIndex(CREATED_BY + ID);
      int textIndex = cursor.getColumnIndex(TEXT);

      do {
        Comment comment = new Comment();

        int id = cursor.getInt(idIndex);
        String createdById = cursor.getString(createdByIdIndex);
        String text = cursor.getString(textIndex);

        comment.setId(id);
        comment.setText(text);
        if (createdById != null)
          comment.setCreatedBy(getUser(createdById));

        comments.add(comment);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return comments;
  }

  public ArrayList<Template> getTemplates() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Template> templates = new ArrayList<>();
    Cursor cursor = db.query(TEMPLATES, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int nameIndex = cursor.getColumnIndex(SUBJECT);
      int typeIndex = cursor.getColumnIndex(TYPE);
      int contentIndex = cursor.getColumnIndex(CONTENT);
      int createdIndex = cursor.getColumnIndex(CREATED);

      do {
        Template template = new Template();

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);
        String type = cursor.getString(typeIndex);
        String created = cursor.getString(createdIndex);
        String content = cursor.getString(contentIndex);

        template.setType(type);
        template.setCreated(created);
        template.setContent(content);
        template.setId(id);
        template.setSubject(name);

        templates.add(template);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return templates;
  }

  public ArrayList<Department> getDepartments() {
    SQLiteDatabase db = this.getWritableDatabase();

    ArrayList<Department> departments = new ArrayList<>();
    Cursor cursor = db.query(DEPARTMENTS, null, null, null, null, null, null);

    if (cursor.moveToFirst()) {
      int idIndex = cursor.getColumnIndex(ID);
      int nameIndex = cursor.getColumnIndex(NAME);

      do {
        Department department = new Department();

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);

        department.setId(id);
        department.setName(name);

        departments.add(department);

      } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return departments;
  }
}
