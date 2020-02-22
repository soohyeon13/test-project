package kr.ac.jejunu.line.ui.fragment

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.image_url_layout.*
import kr.ac.jejunu.line.BR
import kr.ac.jejunu.line.R
import kr.ac.jejunu.line.databinding.BottomSheetLayoutBinding
import kr.ac.jejunu.line.db.database.MemoRepository
import kr.ac.jejunu.line.db.entity.Image
import kr.ac.jejunu.line.db.entity.StorageImage
import kr.ac.jejunu.line.ui.adapter.MemoAddImageAdapter
import kr.ac.jejunu.line.ui.viewmodel.BottomSheetViewModel

class BottomSheetDialog : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetLayoutBinding
    lateinit var viewModel: BottomSheetViewModel
    val CAMERA_REQUEST_CODE = 1
    private val PERMISSION_CODE : Int= 1000
    private val IMAGE_CATURE_CODE : Int = 1001
    private val IMAGE_PICK_CODE :Int = 1002
    private val takePicture = "takePicture"
    private val getPicture = "getPicture"
    var imageUri :Uri? = null
    private val imageAdapter = MemoAddImageAdapter()
    private var imageList : MutableList<Uri> = mutableListOf()
    private val repository: MemoRepository by lazy {
        MemoRepository(requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_layout, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(BottomSheetViewModel::class.java)
        binding.setVariable(BR.viewModel, viewModel)
        initObserver()
        return binding.root
    }

    private fun initObserver() {

        with(viewModel) {
            clickTakePicture.observe(viewLifecycleOwner, Observer {
                checkPermission(takePicture)
            })
            clickGetPicture.observe(viewLifecycleOwner, Observer {
                checkPermission1()
            })
            clickCancel.observe(viewLifecycleOwner, Observer {
                this@BottomSheetDialog.dismiss()
            })
            clickUrl.observe(viewLifecycleOwner, Observer {
                putUrlDialog()
            })
        }
    }

    private fun putUrlDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        builder.setTitle("Put Url")
        val dialogLayout = inflater.inflate(R.layout.image_url_layout,null)
        builder.setView(dialogLayout)
        builder.setPositiveButton("OK") {
            dialogInterface,i -> Toast.makeText(requireContext(),"test",Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    private fun checkPermission1() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity!!.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permission,PERMISSION_CODE)
            }else{
                pickImageFromGallery()
            }
        }else{
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
        startActivityForResult(intent,IMAGE_PICK_CODE)
    }

    private fun checkPermission(check : String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity!!.checkSelfPermission(
                    Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED ||
                activity!!.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                val permission = arrayOf(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission,PERMISSION_CODE)
            }else{
                openCamera()
            }
        }else{
            openCamera()
        }
    }

    private fun openCamera() {
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE,"New Picture")
        value.put(MediaStore.Images.Media.DESCRIPTION,"From Camera")

        imageUri = activity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,value)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
        startActivityForResult(cameraIntent,IMAGE_CATURE_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                }else {
                    Toast.makeText(requireContext(), "permission denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //갤러리에서 uri 받는 부분
        if(requestCode == IMAGE_PICK_CODE) {
            when(resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        when {
                            it.data != null -> imageList.add(it.data!!)
                            it.clipData != null ->  {
                                val clip = it.clipData
                                val count = clip?.itemCount

                                for (i in 0 until count!!) {
                                    val item = clip.getItemAt(i).uri
                                    imageList.add(item)
                                }
                            }
                            else -> {}
                        }
                        //넣어주는 부분
                        imageAdapter.setImages(imageList as List<Image>)
                    }
                }
                else -> {}
            }
            Log.d("test dialog", data!!.clipData.toString())
        }else if (resultCode == Activity.RESULT_OK) {
            Log.d("camera test", imageUri.toString())
        }
        dismiss()
    }
}