package kr.ac.jejunu.line.di

import kr.ac.jejunu.line.db.database.MemoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val roomModule = module {
    single { MemoDatabase.getInstance(androidApplication())!! }
    single(createOnStart = false){get<MemoDatabase>().memoDataDao()}
    single(createOnStart =false){get<MemoDatabase>().memoImageDataDao()}
}